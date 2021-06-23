package stud.team.pwsbackend.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;
import stud.team.pwsbackend.dto.StreamDto;
import stud.team.pwsbackend.security.SimpleAuthentication;

@Service
@Slf4j
public class WsService {
    private final UserStreamService userStreamService;

    private final SimpMessagingTemplate simpMessagingTemplate;

    private final StreamService streamService;

    public WsService(UserStreamService userStreamService, SimpMessagingTemplate simpMessagingTemplate, StreamService streamService) {
        this.userStreamService = userStreamService;
        this.simpMessagingTemplate = simpMessagingTemplate;
        this.streamService = streamService;
    }

    @EventListener
    public void onConnectEvent(SessionConnectEvent event) {
        log.info("client {} connected", event.getUser());
    }

    @EventListener
    public void onDisconnectEvent(SessionDisconnectEvent event) throws Exception {
        log.info("client {} disconnected", event.getUser());
        SimpleAuthentication user;
        if (!(event.getUser() instanceof SimpleAuthentication)) {
            return;
        }

        user = (SimpleAuthentication) event.getUser();
        var userId = user.getUserId();

        log.info("client {} streams: {}", userId, userStreamService.getAllStreams(userId));

        var activeStreamOptional = userStreamService.getAllStreams(userId).stream()
                .filter(StreamDto::getActiveStream)
                .findFirst();

        if (activeStreamOptional.isEmpty()) {
            return;
        }
        StreamDto activeStream = activeStreamOptional.get();
        long streamId = activeStream.getIdStream();
        log.info("stop stream with id: {}", streamId);

        String topic = "/topic/streams/" + streamId + "/events";
        String message = "{\"type\": \"state\",\"state\":\"pause\"}";
        simpMessagingTemplate.convertAndSend(topic, message);

        streamService.setStopStream(streamId, true);
    }

}
