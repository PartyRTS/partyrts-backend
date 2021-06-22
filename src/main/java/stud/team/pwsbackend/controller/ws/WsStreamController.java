package stud.team.pwsbackend.controller.ws;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import stud.team.pwsbackend.service.StreamService;

@Controller
@Slf4j
public class WsStreamController {

    private final StreamService streamService;

    public WsStreamController(StreamService streamService) {
        this.streamService = streamService;
    }

    @MessageMapping("/streams/{streamId}/start")
    public void start(@DestinationVariable long streamId) {
        // Todo
        log.info("start stream with id: {}", streamId);
    }

    @MessageMapping("/streams/{streamId}/next")
    public void next(@DestinationVariable long streamId) throws Exception {
        log.info("request next video in stream with id: {}", streamId);
        streamService.setNextVideoStream(streamId);
    }
}
