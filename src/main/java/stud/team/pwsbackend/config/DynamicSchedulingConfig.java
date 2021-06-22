package stud.team.pwsbackend.config;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import stud.team.pwsbackend.service.VoteService;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
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

    private Map<Long, LocalDateTime> votesMap = new HashMap<>();

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
                        for(Long vote : votesMap.keySet()){
                            if(0 >= Duration.between(time, votesMap.get(vote)).getSeconds()){
                                voteService.closeVoteById(vote);
                                log.info("close vote: " + vote + " \n");
                                votesMap.remove(vote);
                            }
                        }
                    }
                },
                new Trigger() {
                    @Override
                    public Date nextExecutionTime(TriggerContext context) {
                        int plus = 4000;
                        if(!votesMap.isEmpty()){
                            Duration duration = null;
                            LocalDateTime time = LocalDateTime.now();
                            for(Long vote : votesMap.keySet()){
                                if(duration == null || duration.getSeconds() > Duration.between(time, votesMap.get(vote)).getSeconds()){
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
                }
        );
    }

    public void addVoteToMap(Long voteId,LocalDateTime time){
        votesMap.put(voteId,time);
        log.info("add vote to map with id: " + voteId + " \n");
    }
}
