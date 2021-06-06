package stud.team.pwsbackend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import stud.team.pwsbackend.domain.Category;
import stud.team.pwsbackend.domain.Message;
import stud.team.pwsbackend.domain.Stream;
import stud.team.pwsbackend.domain.Vote;
import stud.team.pwsbackend.dto.CategoryDto;
import stud.team.pwsbackend.dto.MessageDto;
import stud.team.pwsbackend.dto.StreamDto;
import stud.team.pwsbackend.dto.VoteDto;
import stud.team.pwsbackend.mapper.CategoryMapper;
import stud.team.pwsbackend.mapper.MessageMapper;
import stud.team.pwsbackend.mapper.StreamMapper;
import stud.team.pwsbackend.mapper.VoteMapper;
import stud.team.pwsbackend.repository.StreamRepository;
import stud.team.pwsbackend.repository.VoteRepository;
import stud.team.pwsbackend.service.StreamService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StreamServiceImpl implements StreamService {

    private StreamRepository streamRepository;
    private VoteRepository voteRepository;
    private StreamMapper streamMapper;
    private MessageMapper messageMapper;
    private CategoryMapper categoryMapper;
    private VoteMapper voteMapper;

    @Override
    public List<StreamDto> getAllStream() {
        List<Stream> streams = streamRepository.findAll();
        return streamMapper.listStreamToListDto(streams);
    }

    @Override
    public void deleteAllStream() {
        streamRepository.deleteAll();
    }

    @Override
    public StreamDto getStreamById(Long streamId) {
        Optional<Stream> stream = streamRepository.findById(streamId);
        return stream.map(streamMapper::streamToDto).orElse(null);
    }

    @Override
    public StreamDto addStream(StreamDto streamDto) {
        Stream stream = streamMapper.dtoToStream(streamDto);
        stream = streamRepository.save(stream);
        return streamMapper.streamToDto(stream);
    }

    @Override
    public void deleteStreamById(Long streamId) {
        streamRepository.deleteById(streamId);
    }

    @Override
    public List<MessageDto> getAllMessageByStream(Long streamId) {
        Optional<Stream> stream = streamRepository.findById(streamId);
        if (stream.isPresent()) {
            List<Message> messages = new ArrayList<>(stream.get().getMessages());
            return messageMapper.mapToDto(messages);
        }
        return null;
    }

    @Override
    public List<CategoryDto> getAllCategoryByStream(Long streamId) {
        Optional<Stream> stream = streamRepository.findById(streamId);
        if (stream.isPresent()) {
            List<Category> categories = new ArrayList<>(stream.get().getCategories());
            return categoryMapper.listCategoryToListDto(categories);
        }
        return null;
    }

    @Override
    public List<VoteDto> getAllVoteByStream(Long streamId) {
        Optional<Stream> stream = streamRepository.findById(streamId);
        if (stream.isPresent()) {
            List<Vote> votes = new ArrayList<>(stream.get().getVote());
            return voteMapper.listVoteToListDto(votes);
        }
        return null;
    }

    @Override
    public void addVoteToStream(Long streamId, Long voteId) throws Exception {
        Stream stream = streamRepository.findById(streamId).orElseThrow(Exception::new);
        Vote vote = voteRepository.findById(voteId).orElseThrow(Exception::new);
        vote.setStream(stream);
        voteRepository.save(vote);
    }

    @Autowired
    public void setStreamRepository(StreamRepository streamRepository) {
        this.streamRepository = streamRepository;
    }

    @Autowired
    public void setVoteRepository(VoteRepository voteRepository) {
        this.voteRepository = voteRepository;
    }

    @Autowired
    public void setStreamMapper(StreamMapper streamMapper) {
        this.streamMapper = streamMapper;
    }

    @Autowired
    public void setMessageMapper(MessageMapper messageMapper) {
        this.messageMapper = messageMapper;
    }

    @Autowired
    public void setCategoryMapper(CategoryMapper categoryMapper) {
        this.categoryMapper = categoryMapper;
    }

    @Autowired
    public void setVoteMapper(VoteMapper voteMapper) {
        this.voteMapper = voteMapper;
    }
}
