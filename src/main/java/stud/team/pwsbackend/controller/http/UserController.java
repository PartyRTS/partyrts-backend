package stud.team.pwsbackend.controller.http;


import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import stud.team.pwsbackend.domain.Password;
import stud.team.pwsbackend.domain.User;
import stud.team.pwsbackend.dto.LoginRequestDto;
import stud.team.pwsbackend.dto.NewUserDto;
import stud.team.pwsbackend.dto.UpdatePasswordRequestDto;
import stud.team.pwsbackend.dto.UserDto;
import stud.team.pwsbackend.exception.message.IncorrectCredentialsException;
import stud.team.pwsbackend.exception.user.UserNotFoundException;
import stud.team.pwsbackend.service.UserFriendRequestService;
import stud.team.pwsbackend.service.UserService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/users")
@Slf4j
@Validated
public class UserController {

    private final UserService userService;
    private final UserFriendRequestService friendRequestService;

    public UserController(UserService userService, UserFriendRequestService friendRequestService) {
        this.userService = userService;
        this.friendRequestService = friendRequestService;
    }

    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @DeleteMapping
    public void deleteAllUsers() {
        userService.deleteAllUsers();
    }

    @GetMapping("/{userId}")
    public UserDto getUserById(@PathVariable long userId) throws UserNotFoundException {
        return userService.getUser(userId);
    }

    @PostMapping
    public UserDto addUser(@Valid @RequestBody NewUserDto userDto) {
        return userService.addUser(userDto);
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable long userId) throws UserNotFoundException {
        userService.deleteUser(userId);
    }

    @PutMapping("/{userId}/password")
    public void updateUserPassword(@PathVariable long userId, @RequestBody UserDto userDto) throws UserNotFoundException {
        userService.updateUser(userId, userDto);
    }

    @PostMapping("/login")
    public UserDto login(@Valid @RequestBody LoginRequestDto loginRequestDto) throws IncorrectCredentialsException {
        return userService.login(loginRequestDto);
    }

    @PostMapping("/register")
    public UserDto register(@Valid @RequestBody NewUserDto newUserDto) {
        return userService.register(newUserDto);
    }

    @PostMapping("/{userId}/updUser")
    public void updateUser(@PathVariable long userId,@Valid @RequestBody UserDto userDto) throws UserNotFoundException {
        userService.updateUser(userId,userDto);
    }

    @PostMapping("/{userId}/updPassword")
    public void updatePassword(@PathVariable long userId,@Valid @RequestBody UpdatePasswordRequestDto updPassword) throws UserNotFoundException {
        userService.updatePassword(userId,updPassword);
    }

    @GetMapping("/{userId}/friends")
    public List<UserDto> getAllFriends(@PathVariable long userId) throws UserNotFoundException {
        return friendRequestService.getAllFriends(userId);
    }

    @DeleteMapping("/{userId}/deleteFriend")
    public void deleteFriend(@PathVariable long userId,@RequestBody long friendId) throws UserNotFoundException {
        friendRequestService.deleteFriend(userId,friendId);
    }

}
