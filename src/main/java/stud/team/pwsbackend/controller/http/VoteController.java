package stud.team.pwsbackend.controller.http;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import stud.team.pwsbackend.domain.Vote;
import stud.team.pwsbackend.dto.VoteDto;
import stud.team.pwsbackend.service.VoteService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/vote",
        produces = "application/json")
@Slf4j
public class VoteController {
    private final VoteService voteService;

    public VoteController(VoteService voteService) {
        this.voteService = voteService;
    }

    @GetMapping
    public List<VoteDto> getAllVotes() {
        return voteService.getAllVotes();
    }

    @GetMapping("/{voteId}")
    public VoteDto getVoteById(@PathVariable Long voteId) {
        return voteService.getVoteById(voteId);
    }

    @PostMapping("/{voteId}/close")
    public void closeVoteById(@PathVariable Long voteId) throws Exception {
        voteService.closeVoteById(voteId);
    }
}
