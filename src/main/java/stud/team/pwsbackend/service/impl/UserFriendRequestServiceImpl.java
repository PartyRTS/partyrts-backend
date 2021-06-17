package stud.team.pwsbackend.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import stud.team.pwsbackend.domain.User;
import stud.team.pwsbackend.dto.MessageDto;
import stud.team.pwsbackend.dto.UserDto;
import stud.team.pwsbackend.exception.message.IncorrectCredentialsException;
import stud.team.pwsbackend.exception.user.UserNotFoundException;
import stud.team.pwsbackend.mapper.UserMapper;
import stud.team.pwsbackend.repository.UserRepository;
import stud.team.pwsbackend.service.UserFriendRequestService;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@Slf4j
public class UserFriendRequestServiceImpl implements UserFriendRequestService {
    private UserRepository userRepository;
    private UserMapper userMapper;

    @Override
    public List<UserDto> getAllFriendRequests(long userId) throws UserNotFoundException {
        var user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
        return userMapper.mapToDto(user.getFriendRequest());
    }

    @Override
    public void addFriendRequest(long userId, long senderId) throws UserNotFoundException {
        var firstUser = userRepository.findById(userId)
                .orElseThrow();
        var secondUser = userRepository.findById(senderId)
                .orElseThrow();
        firstUser.getFriendRequest().add(secondUser);
        userRepository.save(firstUser);
    }

    @Override
    public void deleteFriendRequest(long userId, long senderId) throws UserNotFoundException {
        var user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
        user.getFriendRequest().removeIf(friendreq -> friendreq.getIdUser() == senderId);
        userRepository.save(user);
    }

    @Override
    public void acceptFriendRequest(long userId, long senderId) throws UserNotFoundException {
        var firstUser = userRepository.findById(userId)
                .orElseThrow();
        var secondUser = userRepository.findById(senderId)
                .orElseThrow();
        if(!firstUser.getFriends().contains(secondUser)) {
            firstUser.getFriends().add(secondUser);
        }

        deleteFriendRequest(userId, senderId);
        deleteFriendRequest(senderId, userId);
    }

    @Override
    public void declineFriendRequest(long userId, long senderId) throws UserNotFoundException {
        deleteFriendRequest(userId, senderId);
    }

    @Override
    public List<UserDto> getAllFriends(long userId) throws UserNotFoundException {
        var user = userRepository.findById(userId)
                .orElseThrow();
        return userMapper.mapToDto(user.getFriends());
    }

    @Override
    public void deleteFriend(long userId, long friendId) throws UserNotFoundException {
        var firstUser = userRepository.findById(userId)
                .orElseThrow();
        var secondUser = userRepository.findById(friendId)
                .orElseThrow();
        firstUser.getFriends().remove(secondUser);
        secondUser.getFriends().remove(firstUser);
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }
}
