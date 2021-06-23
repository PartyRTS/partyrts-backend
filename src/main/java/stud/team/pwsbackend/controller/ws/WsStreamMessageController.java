package stud.team.pwsbackend.controller.ws;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import stud.team.pwsbackend.dto.MessageDto;
import stud.team.pwsbackend.service.StreamService;

@Controller
@Slf4j
public class WsStreamMessageController {

    private final StreamService streamService;

    public WsStreamMessageController(StreamService streamService) {
        this.streamService = streamService;
    }

    @MessageMapping("/messages/send")
    public void sendMessage(MessageDto messageDto) {
        log.info("received chat message by ws: {}", messageDto);
        long streamId = messageDto.getIdStream();
        streamService.addMessageToStream(streamId, messageDto);
    }
}
