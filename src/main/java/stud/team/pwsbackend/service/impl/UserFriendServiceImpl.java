package stud.team.pwsbackend.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import stud.team.pwsbackend.dto.MessageDto;
import stud.team.pwsbackend.dto.UserDto;
import stud.team.pwsbackend.exception.user.UserNotFoundException;
import stud.team.pwsbackend.service.UserFriendService;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@Slf4j
public class UserFriendServiceImpl implements UserFriendService {
    @Override
    public List<UserDto> getAllFriends(long userId) throws UserNotFoundException {
        return null;
    }

    @Override
    public void deleteAllFriends(long userId) throws UserNotFoundException {

    }

    @Override
    public MessageDto addFriend(long userId, long senderId) throws UserNotFoundException {
        return null;
    }

    @Override
    public void deleteFriend(long userId, long senderId) throws UserNotFoundException {

    }
}
