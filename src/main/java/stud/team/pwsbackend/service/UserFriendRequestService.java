package stud.team.pwsbackend.service;

import org.springframework.stereotype.Service;
import stud.team.pwsbackend.dto.MessageDto;
import stud.team.pwsbackend.dto.UserDto;
import stud.team.pwsbackend.exception.user.UserNotFoundException;

import java.util.List;

@Service
public interface UserFriendRequestService {
    List<UserDto> getAllFriendRequests(long userId) throws UserNotFoundException;

    void addFriendRequest(long userId, long senderId) throws UserNotFoundException;

    void deleteFriendRequest(long userId, long senderId) throws UserNotFoundException;

    void acceptFriendRequest(long userId, long senderId) throws UserNotFoundException;

    void declineFriendRequest(long userId, long senderId) throws UserNotFoundException;

    List<UserDto> getAllFriends(long userId) throws UserNotFoundException;

    void deleteFriend(long userId, long senderId) throws UserNotFoundException;
}
