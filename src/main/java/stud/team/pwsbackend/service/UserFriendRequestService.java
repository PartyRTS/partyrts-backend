package stud.team.pwsbackend.service;

import org.springframework.stereotype.Service;
import stud.team.pwsbackend.dto.MessageDto;
import stud.team.pwsbackend.dto.UserDto;
import stud.team.pwsbackend.exception.user.UserNotFoundException;

import java.util.List;

@Service
public interface UserFriendRequestService {
    List<UserDto> getAllFriendRequests(long userId) throws UserNotFoundException;

    void deleteAllFriendRequests(long userId) throws UserNotFoundException;

    MessageDto addFriendRequest(long userId, long senderId) throws UserNotFoundException;

    void deleteFriendRequest(long userId, long senderId) throws UserNotFoundException;

    void acceptFriendRequest(long userId, long senderId) throws UserNotFoundException;

    void declineFriendRequest(long userId, long senderId) throws UserNotFoundException;
}
