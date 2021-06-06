package stud.team.pwsbackend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import stud.team.pwsbackend.domain.UserVote;
import stud.team.pwsbackend.dto.UserVoteDto;
import stud.team.pwsbackend.mapper.UserVoteMapper;
import stud.team.pwsbackend.repository.UserVoteRepository;
import stud.team.pwsbackend.service.UserVoteService;

import java.util.List;
import java.util.Optional;

@Service
public class UserVoteServiceImpl implements UserVoteService {

    private UserVoteRepository userVoteRepository;
    private UserVoteMapper userVoteMapper;

    @Override
    public List<UserVoteDto> getAllUserVote() {
        List<UserVote> userVotes = userVoteRepository.findAll();
        return userVoteMapper.listUserVoteToListDto(userVotes);
    }

    @Override
    public void deleteAllUserVote() {
        userVoteRepository.deleteAll();
    }

    @Override
    public UserVoteDto getUserVoteById(Long userVoteId) {
        Optional<UserVote> userVote = userVoteRepository.findById(userVoteId);
        return userVote.map(userVoteMapper::userVoteToDto).orElse(null);
    }

    @Override
    public UserVoteDto addUserVote(UserVoteDto userVoteDto) {
        UserVote userVote = userVoteMapper.dtoToUserVote(userVoteDto);
        userVote = userVoteRepository.save(userVote);
        return userVoteMapper.userVoteToDto(userVote);
    }

    @Override
    public void deleteUserVoteById(Long userVoteId) {
        userVoteRepository.deleteById(userVoteId);
    }

    @Autowired
    public void setUserVoteRepository(UserVoteRepository userVoteRepository) {
        this.userVoteRepository = userVoteRepository;
    }

    @Autowired
    public void setUserVoteMapper(UserVoteMapper userVoteMapper) {
        this.userVoteMapper = userVoteMapper;
    }
}
