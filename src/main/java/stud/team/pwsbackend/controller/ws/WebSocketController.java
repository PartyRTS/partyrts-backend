package stud.team.pwsbackend.controller.ws;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
@Slf4j
public class WebSocketController {

    @MessageMapping("/message")
    @SendTo("/topic/message")
    public String sendMessage(String message) {
        log.info(message);
        return "received message: " + message;
    }
}
