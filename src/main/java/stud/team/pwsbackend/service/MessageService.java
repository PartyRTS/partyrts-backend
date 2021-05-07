package stud.team.pwsbackend.service;

import stud.team.pwsbackend.dto.MessageDto;
import stud.team.pwsbackend.exception.message.MessageNotFoundException;

import java.util.List;

public interface MessageService {
    List<MessageDto> getAllMessages();

    MessageDto addMessage(MessageDto messageDto);

    void deleteAllMessages();

    MessageDto getMessage(long userId) throws MessageNotFoundException;

    void updateMessage(long userId, MessageDto messageDto) throws MessageNotFoundException;

    void deleteMessage(long userId) throws MessageNotFoundException;
}
