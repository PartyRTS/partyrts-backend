package stud.team.pwsbackend.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import stud.team.pwsbackend.dto.MessageDto;
import stud.team.pwsbackend.exception.message.MessageNotFoundException;
import stud.team.pwsbackend.service.MessageService;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@Slf4j
public class MessageServiceImpl implements MessageService {
    @Override
    public List<MessageDto> getAllMessages() {
        return null;
    }

    @Override
    public MessageDto addMessage(MessageDto messageDto) {
        return null;
    }

    @Override
    public void deleteAllMessages() {

    }

    @Override
    public MessageDto getMessage(long userId) throws MessageNotFoundException {
        return null;
    }

    @Override
    public void updateMessage(long userId, MessageDto messageDto) throws MessageNotFoundException {

    }

    @Override
    public void deleteMessage(long userId) throws MessageNotFoundException {

    }
}
