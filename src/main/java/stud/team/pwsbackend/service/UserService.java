package stud.team.pwsbackend.service;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import stud.team.pwsbackend.domain.User;
import stud.team.pwsbackend.dto.*;
import stud.team.pwsbackend.exception.message.IncorrectCredentialsException;
import stud.team.pwsbackend.exception.user.UserNotFoundException;

import java.util.List;

@Service
public interface UserService {

    List<UserDto> getAllUsers();

    UserDto addUser(NewUserDto userDto);

    void deleteAllUsers();

    UserDto getUser(long userId) throws UserNotFoundException;

    void updateUser(long userId, UserDto userDto) throws UserNotFoundException;

    void updatePassword(long userId, UpdatePasswordRequestDto updPassword) throws UserNotFoundException;

    void deleteUser(long userId) throws UserNotFoundException;

    void addRoleToUser(long userId,long roleId) throws UserNotFoundException;

    void deleteRoleByUser(long userId,long roleId) throws UserNotFoundException;

    void setBanStatusByUser(long userId,boolean ban) throws UserNotFoundException;

    UserDto login(LoginRequestDto loginRequestDto) throws IncorrectCredentialsException;

    UserDto register(NewUserDto reguserDto);

    List<UserDto> findUsersByName(String searchString);

    List<GlobalRoleDto> getAllRolesByUser(long userId) throws UserNotFoundException;

    StreamDto getActiveStream(long userId) throws UserNotFoundException;
}
