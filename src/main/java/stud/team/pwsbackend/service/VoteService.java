package stud.team.pwsbackend.service;

import stud.team.pwsbackend.dto.UserVoteDto;
import stud.team.pwsbackend.dto.VoteDto;

import java.util.List;

public interface VoteService {

    List<VoteDto> getAllVotes();

    VoteDto getVoteById(Long voteId);

    void closeVoteById(Long voteId) throws Exception;

    double getPercentPlusVotes(Long voteId) throws Exception;

    UserVoteDto addUserVoteToVote(Long voteId,UserVoteDto userVoteDto) throws Exception;

}
