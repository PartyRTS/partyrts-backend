package stud.team.pwsbackend.config;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import stud.team.pwsbackend.service.StreamService;
import stud.team.pwsbackend.service.VoteService;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Configuration
@EnableScheduling
@Slf4j
public class DynamicSchedulingConfig implements SchedulingConfigurer {

    @Autowired
    private VoteService voteService;

    private final Map<Long, LocalDateTime> votesMap = new HashMap<>();
    @Autowired
    private StreamService streamService;
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Bean
    public Executor taskExecutor() {
        return Executors.newSingleThreadScheduledExecutor();
    }

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.setScheduler(taskExecutor());
        taskRegistrar.addTriggerTask(
                new Runnable() {
                    @SneakyThrows
                    @Override
                    public void run() {
                        LocalDateTime time = LocalDateTime.now();
                        for (Long voteId : votesMap.keySet()) {
                            if (0 >= Duration.between(time, votesMap.get(voteId)).getSeconds()) {
                                voteService.closeVoteById(voteId);
                                log.info("close vote: " + voteId + " \n");
                                votesMap.remove(voteId);

                                var vote = voteService.getVoteById(voteId);
                                var streamId = vote.getIdStream();
                                String topic = "/topic/streams/" + streamId + "/events";
                                String message = "{\"type\": \"vote\"}";
                                simpMessagingTemplate.convertAndSend(topic, message);
                            }
                        }
                    }
                },
                context -> {
                    int plus = 40000;
                    if (!votesMap.isEmpty()) {
                        Duration duration = null;
                        LocalDateTime time = LocalDateTime.now();
                        for (Long vote : votesMap.keySet()) {
                            if (duration == null || duration.getSeconds() > Duration.between(time, votesMap.get(vote)).getSeconds()) {
                                duration = Duration.between(time, votesMap.get(vote));
                            }
                        }
                        plus = (int) duration.getSeconds() * 1000 + 1000;
                        log.info("new plus time: " + plus + " \n");
                    }
                    Optional<Date> lastCompletionTime =
                            Optional.ofNullable(context.lastCompletionTime());
                    Instant nextExecutionTime =
                            lastCompletionTime.orElseGet(Date::new).toInstant()
                                    .plusMillis(plus);
                    return Date.from(nextExecutionTime);
                }
        );
    }

    public void addVoteToMap(Long voteId, LocalDateTime time) {
        votesMap.put(voteId, time);
        log.info("add vote to map with id: " + voteId + " \n");
    }
}
