package stud.team.pwsbackend.controller.ws;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import stud.team.pwsbackend.service.StreamService;

@Controller
@Slf4j
public class WsStreamController {

    private final StreamService streamService;
    private final SimpMessagingTemplate simpMessagingTemplate;

    public WsStreamController(StreamService streamService, SimpMessagingTemplate simpMessagingTemplate) {
        this.streamService = streamService;
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @MessageMapping("/streams/{streamId}/start")
    public void start(@DestinationVariable long streamId) throws Exception {
        log.info("start stream with id: {}", streamId);

        String topic = "/topic/streams/" + streamId + "/events";
        String message = "{\"type\": \"state\",\"state\":\"play\"}";
        simpMessagingTemplate.convertAndSend(topic, message);

        streamService.setStopStream(streamId, false);
    }

    @MessageMapping("/streams/{streamId}/next")
    public void next(@DestinationVariable long streamId) throws Exception {
        log.info("request next video in stream with id: {}", streamId);
        streamService.setNextVideoStream(streamId);

        String topic = "/topic/streams/" + streamId + "/events";
        String message = "{\"type\": \"next\"}";
        simpMessagingTemplate.convertAndSend(topic, message);
    }
}
