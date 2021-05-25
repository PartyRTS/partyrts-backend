package stud.team.pwsbackend.controller.http;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import stud.team.pwsbackend.dto.UserVoteDto;
import stud.team.pwsbackend.service.UserVoteService;

import java.util.List;

@RestController
@RequestMapping(path ="api/v1/userVotes",
        produces = "application/json")
@Slf4j
public class UserVoteController {

    UserVoteService userVoteService;

    public UserVoteController(UserVoteService userVoteService) {
        this.userVoteService = userVoteService;
    }

    @GetMapping
    public List<UserVoteDto> getAllUserVote() {
        return userVoteService.getAllUserVote();
    }

    @DeleteMapping
    public void deleteAllUserVote() {
        userVoteService.deleteAllUserVote();
    }

    @GetMapping("/{userVoteId}")
    public UserVoteDto getUserVoteById(@PathVariable Long userVoteId) {
        return userVoteService.getUserVoteById(userVoteId);
    }

    @PostMapping
    public UserVoteDto addUserVote(@RequestBody UserVoteDto userVoteDto) {
        return userVoteService.addUserVote(userVoteDto);
    }

    @DeleteMapping("/{userVoteId}")
    public void deleteUserVoteById(@PathVariable Long userVoteId) {
        userVoteService.deleteUserVoteById(userVoteId);
    }
}
