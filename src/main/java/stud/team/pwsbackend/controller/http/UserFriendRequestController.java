package stud.team.pwsbackend.controller.http;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import stud.team.pwsbackend.dto.UserDto;
import stud.team.pwsbackend.exception.user.UserNotFoundException;
import stud.team.pwsbackend.service.UserFriendRequestService;

import java.util.List;
import java.util.Map;

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
    public void addFriendRequest(@PathVariable long userId, @RequestBody Map<String, Long> param) throws UserNotFoundException {
        var recipientId = param.get("recipientId");
        userFriendRequestService.addFriendRequest(recipientId, userId);
    }

    @PostMapping("/accept")
    void acceptFriendRequest(@PathVariable long userId, @RequestBody Map<String, Long> param) throws UserNotFoundException {
        var senderId = param.get("senderId");
        userFriendRequestService.acceptFriendRequest(userId, senderId);
    }

    @PostMapping("/decline")
    void declineFriendRequest(@PathVariable long userId, @RequestBody Map<String, Long> param) throws UserNotFoundException {
        var senderId = param.get("senderId");
        userFriendRequestService.declineFriendRequest(userId, senderId);
    }

}
