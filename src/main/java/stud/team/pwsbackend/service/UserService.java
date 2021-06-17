package stud.team.pwsbackend.service;

import org.springframework.stereotype.Service;
import stud.team.pwsbackend.dto.LoginRequestDto;
import stud.team.pwsbackend.dto.NewUserDto;
import stud.team.pwsbackend.dto.UpdatePasswordRequestDto;
import stud.team.pwsbackend.dto.UserDto;
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

    public UserDto login(LoginRequestDto loginRequestDto) throws IncorrectCredentialsException;

    public UserDto register(NewUserDto reguserDto);
}
