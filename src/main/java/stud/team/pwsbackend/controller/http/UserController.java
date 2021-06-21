package stud.team.pwsbackend.controller.http;


import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import stud.team.pwsbackend.domain.Video;
import stud.team.pwsbackend.dto.*;
import stud.team.pwsbackend.exception.message.IncorrectCredentialsException;
import stud.team.pwsbackend.exception.user.UserNotFoundException;
import stud.team.pwsbackend.service.PlaylistService;
import stud.team.pwsbackend.service.UserFriendRequestService;
import stud.team.pwsbackend.service.UserService;
import stud.team.pwsbackend.service.VideoService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/users")
@Slf4j
@Validated
public class UserController {

    private final UserService userService;
    private final UserFriendRequestService friendRequestService;
    private final VideoService videoService;
    private final PlaylistService playlistService;

    public UserController(UserService userService, UserFriendRequestService friendRequestService, VideoService videoService, PlaylistService playlistService) {
        this.userService = userService;
        this.friendRequestService = friendRequestService;
        this.videoService = videoService;
        this.playlistService = playlistService;
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

    @PostMapping("/login")
    public UserDto login(@Valid @RequestBody LoginRequestDto loginRequestDto) throws IncorrectCredentialsException {
        return userService.login(loginRequestDto);
    }

    @PostMapping("/register")
    public UserDto register(@Valid @RequestBody NewUserDto newUserDto) {
        return userService.register(newUserDto);
    }

    @PutMapping("/{userId}")
    public void updateUser(@PathVariable long userId, @Valid @RequestBody UserDto userDto) throws UserNotFoundException {
        userService.updateUser(userId, userDto);
    }

    @PutMapping("/{userId}/password")
    public void updatePassword(@PathVariable long userId, @Valid @RequestBody UpdatePasswordRequestDto updPassword) throws UserNotFoundException {
        userService.updatePassword(userId, updPassword);
    }

    @GetMapping("/{userId}/friends")
    public List<UserDto> getAllFriends(@PathVariable long userId) throws UserNotFoundException {
        return friendRequestService.getAllFriends(userId);
    }

    @DeleteMapping("/{userId}/friends/{friendId}")
    public void deleteFriend(@PathVariable long userId, @PathVariable long friendId) throws UserNotFoundException {
        friendRequestService.deleteFriend(userId, friendId);
    }

    @GetMapping("/search")
    public List<UserDto> findUsersByName(@RequestParam("search") String search){
        return userService.findUsersByName(search);
    }

    @GetMapping("/{userId}/videos")
    public List<VideoDto> getUserVideos(@PathVariable long userId) throws UserNotFoundException {
        return videoService.getAllVideoByUser(userId);
    }

    @GetMapping("/{userId}/playlists")
    public List<PlaylistDto> getAllPlaylistsByUser(@PathVariable Long userId) throws UserNotFoundException{
        return playlistService.getAllPlaylistsByUser(userId);
    }

    @PutMapping("/{userId}/addRole")
    public void addRoleToUser(@PathVariable long userId,@RequestBody long roleId) throws UserNotFoundException {
        userService.addRoleToUser(userId,roleId);
    }

    @DeleteMapping("/{userId}/deleteRole")
    public void deleteRoleByUser(@PathVariable long userId,@RequestBody long roleId) throws UserNotFoundException {
        userService.deleteRoleByUser(userId,roleId);
    }

    @PutMapping("/{userId}/setBan")
    public void setBanStatusByUser(@PathVariable long userId,@RequestBody boolean ban) throws UserNotFoundException {
        userService.setBanStatusByUser(userId,ban);
    }

}
