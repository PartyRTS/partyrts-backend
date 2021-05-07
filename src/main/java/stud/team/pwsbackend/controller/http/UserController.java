package stud.team.pwsbackend.controller.http;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import stud.team.pwsbackend.dto.UserDto;
import stud.team.pwsbackend.exception.user.UserNotFoundException;
import stud.team.pwsbackend.service.UserService;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
@Slf4j
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
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
    public UserDto addUser(@RequestBody UserDto userDto) {
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
}
