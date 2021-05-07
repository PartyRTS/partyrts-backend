package stud.team.pwsbackend.service;

import org.springframework.stereotype.Service;
import stud.team.pwsbackend.dto.MessageDto;
import stud.team.pwsbackend.dto.UserDto;
import stud.team.pwsbackend.exception.user.UserNotFoundException;

import java.util.List;

@Service
public interface UserFriendService {
    List<UserDto> getAllFriends(long userId) throws UserNotFoundException;

    void deleteAllFriends(long userId) throws UserNotFoundException;

    MessageDto addFriend(long userId, long senderId) throws UserNotFoundException;

    void deleteFriend(long userId, long senderId) throws UserNotFoundException;
}
