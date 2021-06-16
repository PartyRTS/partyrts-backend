package stud.team.pwsbackend.controller.http;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import stud.team.pwsbackend.dto.UserDto;
import stud.team.pwsbackend.exception.user.UserNotFoundException;
import stud.team.pwsbackend.service.UserFriendRequestService;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/friendRequests",
        produces = "application/json")
@Slf4j
public class FriendRequestController {
    private final UserFriendRequestService friendRequestService;

    public FriendRequestController(UserFriendRequestService friendRequestService) {
        this.friendRequestService = friendRequestService;
    }

    @GetMapping("/{userId}")
    List<UserDto> getAllFriendRequests(@PathVariable long userId) throws UserNotFoundException {
        return friendRequestService.getAllFriendRequests(userId);
    }

    @PostMapping("/{userId}/send")
    public void addFriendRequest(@PathVariable long userId,@RequestBody long recepientId) throws UserNotFoundException {
        friendRequestService.addFriendRequest(recepientId,userId);
    }

    @PostMapping("/{userId}/accept")
    void acceptFriendRequest(@PathVariable long userId,@RequestBody long senderId) throws UserNotFoundException {
        friendRequestService.acceptFriendRequest(userId,senderId);
    }

    @DeleteMapping("/{userId}/decline")
    void declineFriendRequest(@PathVariable long userId,@RequestBody  long senderId) throws UserNotFoundException {
        friendRequestService.declineFriendRequest(userId,senderId);
    }
}
