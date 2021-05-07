package stud.team.pwsbackend.service;

import org.springframework.stereotype.Service;
import stud.team.pwsbackend.dto.GlobalRoleDto;
import stud.team.pwsbackend.exception.user.UserNotFoundException;

import java.util.List;

//TODO нужен вообще?

@Service
public interface UserMessageService {
    List<GlobalRoleDto> getAllMessages(long userId) throws UserNotFoundException;
}
