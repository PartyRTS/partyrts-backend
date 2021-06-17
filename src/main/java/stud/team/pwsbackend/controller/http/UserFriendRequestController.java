package stud.team.pwsbackend.controller.http;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import stud.team.pwsbackend.dto.UserDto;
import stud.team.pwsbackend.exception.user.UserNotFoundException;
import stud.team.pwsbackend.service.UserFriendRequestService;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/users/{userId}/friendRequests",
        produces = "application/json")
@Slf4j
public class UserFriendRequestController {
    private final UserFriendRequestService userFriendRequestService;

    public UserFriendRequestController(UserFriendRequestService userFriendRequestService) {
        this.userFriendRequestService = userFriendRequestService;
    }

    @GetMapping
    List<UserDto> getAllFriendRequests(@PathVariable long userId) throws UserNotFoundException {
        return userFriendRequestService.getAllFriendRequests(userId);
    }

    @PostMapping("/send")
    public void addFriendRequest(@PathVariable long userId, @RequestBody long recipientId) throws UserNotFoundException {
        userFriendRequestService.addFriendRequest(recipientId, userId);
    }

    @PostMapping("/accept")
    void acceptFriendRequest(@PathVariable long userId, @RequestBody long senderId) throws UserNotFoundException {
        userFriendRequestService.acceptFriendRequest(userId, senderId);
    }

    @DeleteMapping("/decline")
    void declineFriendRequest(@PathVariable long userId, @RequestBody long senderId) throws UserNotFoundException {
        userFriendRequestService.declineFriendRequest(userId, senderId);
    }

}
