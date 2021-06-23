package stud.team.pwsbackend.service.impl;

import org.springframework.stereotype.Service;
import stud.team.pwsbackend.dto.StreamDto;
import stud.team.pwsbackend.mapper.StreamMapper;
import stud.team.pwsbackend.repository.UserRepository;
import stud.team.pwsbackend.service.UserStreamService;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserStreamServiceImpl implements UserStreamService {
    private final UserRepository userRepository;
    private final StreamMapper streamMapper;

    public UserStreamServiceImpl(UserRepository userRepository, StreamMapper streamMapper) {
        this.userRepository = userRepository;
        this.streamMapper = streamMapper;
    }

    @Override
    public List<StreamDto> getAllStreams(long userId) {
        var user = userRepository.findById(userId).orElseThrow();
        var streams = user.getUsersStreams();
        return streamMapper.listStreamToListDto(streams);
    }
}
