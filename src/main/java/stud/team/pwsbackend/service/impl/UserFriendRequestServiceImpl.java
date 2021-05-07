package stud.team.pwsbackend.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import stud.team.pwsbackend.dto.MessageDto;
import stud.team.pwsbackend.dto.UserDto;
import stud.team.pwsbackend.exception.user.UserNotFoundException;
import stud.team.pwsbackend.service.UserFriendRequestService;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@Slf4j
public class UserFriendRequestServiceImpl implements UserFriendRequestService {
    @Override
    public List<UserDto> getAllFriendRequests(long userId) throws UserNotFoundException {
        return null;
    }

    @Override
    public void deleteAllFriendRequests(long userId) throws UserNotFoundException {

    }

    @Override
    public MessageDto addFriendRequest(long userId, long senderId) throws UserNotFoundException {
        return null;
    }

    @Override
    public void deleteFriendRequest(long userId, long senderId) throws UserNotFoundException {

    }

    @Override
    public void acceptFriendRequest(long userId, long senderId) throws UserNotFoundException {

    }

    @Override
    public void declineFriendRequest(long userId, long senderId) throws UserNotFoundException {

    }
}
