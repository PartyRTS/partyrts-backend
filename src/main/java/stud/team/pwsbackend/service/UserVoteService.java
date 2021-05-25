package stud.team.pwsbackend.service;

import stud.team.pwsbackend.dto.UserVoteDto;

import java.util.List;

public interface UserVoteService {

    List<UserVoteDto> getAllUserVote();

    void deleteAllUserVote();

    UserVoteDto getUserVoteById(Long userVoteId);

    UserVoteDto addUserVote(UserVoteDto userVoteDto);

    void deleteUserVoteById(Long userVoteId);
}
