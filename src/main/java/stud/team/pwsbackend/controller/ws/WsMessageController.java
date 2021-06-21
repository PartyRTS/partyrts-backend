package stud.team.pwsbackend.controller.ws;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import stud.team.pwsbackend.dto.MessageDto;
import stud.team.pwsbackend.service.StreamService;

@Controller
@Slf4j
public class WsMessageController {

    private final StreamService streamService;
    private final SimpMessagingTemplate simpMessagingTemplate;

    public WsMessageController(StreamService streamService, SimpMessagingTemplate simpMessagingTemplate) {
        this.streamService = streamService;
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @MessageMapping("/messages/send")
    public void sendMessage(MessageDto messageDto) {
        System.out.println(messageDto);
        long streamId = messageDto.getIdStream();
        streamService.addMessageToStream(streamId, messageDto);
    }
}
