package stud.team.pwsbackend.service;

import stud.team.pwsbackend.dto.VoteDto;

import java.util.List;

public interface VoteService {

    List<VoteDto> getAllVotes();

    VoteDto getVoteById(Long voteId);

    void closeVoteById(Long voteId) throws Exception;

}
