package stud.team.pwsbackend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import stud.team.pwsbackend.domain.*;
import stud.team.pwsbackend.dto.*;
import stud.team.pwsbackend.mapper.*;
import stud.team.pwsbackend.repository.*;
import stud.team.pwsbackend.service.StreamService;
import utils.FullPlaylistUtil;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class StreamServiceImpl implements StreamService {

    private StreamRepository streamRepository;
    private VideoRepository videoRepository;
    private VoteAddRepository voteAddRepository;
    private VoteSkipRepository voteSkipRepository;
    private VoteRepository voteRepository;
    private UserRepository userRepository;
    private PlaylistRepository playlistRepository;
    private CategoryRepository categoryRepository;
    private InsertVideosRepository insVideosRepository;
    private MessageRepository messageRepository;
    private StreamMapper streamMapper;
    private MessageMapper messageMapper;
    private CategoryMapper categoryMapper;
    private VoteMapper voteMapper;
    private VideoMapper videoMapper;
    private VoteSkipMapper voteSkipMapper;
    private VoteAddMapper voteAddMapper;
    private InsertVideosMapper insVideosMapper;
    private SimpMessagingTemplate simpMessagingTemplate;

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
    public StreamDto addStream(StreamDto streamDto) throws Exception {
        Stream stream = streamMapper.dtoToStream(streamDto);
        User user = userRepository.findById(streamDto.getIdUser()).orElseThrow(Exception::new);
        Playlist playlist = playlistRepository.findById(streamDto.getIdPlaylist()).orElseThrow(Exception::new);
        stream.setUser(user);
        stream.setPlaylist(playlist);
        stream.setActiveStream(true);
        stream.setCurrentNumberVideo(playlist.getVideoHasPlaylists().get(0).getVideo().getIdVideo());
        stream = streamRepository.save(stream);
        return streamMapper.streamToDto(stream);
    }

    @Override
    public void deleteStreamById(Long streamId) {
        streamRepository.deleteById(streamId);
    }


    // FIXME кидай ошибку, если сущности не существует
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
    public void setNextVideoStream(Long streamId) throws Exception {
        Stream stream = streamRepository.findById(streamId).orElseThrow();
        boolean set = false;
        for(VideoWithNumb video : getFullPlaylistByStream(streamId)){
            if(set){
                stream.setCurrentNumberVideo(video.getIdVideo());
                set = false;
                break;
            }
            if(stream.getCurrentNumberVideo().equals(video.getIdVideo())){
                set = true;
            }
        }
        if(set){
            stream.setActiveStream(false);
        }
    }

    @Override
    public MessageDto addMessageToStream(Long streamId, MessageDto messageDto) {
        var message = messageMapper.mapToEntity(messageDto);
        var stream = streamRepository.findById(streamId).orElseThrow();
        stream.getMessages().add(message);
        message.setStream(stream);

        var user = userRepository.findById(messageDto.getIdUser()).orElseThrow();
        message.setUser(user);

        message = messageRepository.save(message);
        messageDto = messageMapper.mapToDto(message);
        simpMessagingTemplate.convertAndSend("/topic/streams/" + streamId + "/messages", messageDto);
        return messageDto;
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
    public void addVoteAddToStream(Long streamId, VoteAddDto voteAddDto) throws Exception {
        Stream stream = streamRepository.findById(streamId).orElseThrow(Exception::new);
        Video video = videoRepository.findById(voteAddDto.getIdAddVideo()).orElseThrow(Exception::new);
        Vote vote = new Vote();
        vote.setStream(stream);
        vote.setCloseVote(false);
        vote = voteRepository.save(vote);
        VoteAdd voteAdd = new VoteAdd();
        voteAdd.setNumberPrevVideo(voteAddDto.getNumberPrevVideo());
        voteAdd.setAddVideo(video);
        voteAdd.setVote(vote);
        voteAddRepository.save(voteAdd);
    }

    @Override
    public void addVoteSkipToStream(Long streamId, VoteSkipDto voteSkipDto) throws Exception {
        Stream stream = streamRepository.findById(streamId).orElseThrow(Exception::new);
        Vote vote = new Vote();
        vote.setStream(stream);
        vote.setCloseVote(false);
        vote = voteRepository.save(vote);
        VoteSkip voteSkip = new VoteSkip();
        voteSkip.setNumberSkipVideo(voteSkipDto.getNumberSkipVideo());
        voteSkip.setVote(vote);
        voteSkipRepository.save(voteSkip);
    }

    @Override
    public List<VoteAddDto> getAllVoteAddByStream(Long streamId) throws Exception {
        Stream stream = streamRepository.findById(streamId).orElseThrow(Exception::new);
        List<VoteAddDto> voteAddDtos = new ArrayList<>();
        for (Vote vote : stream.getVote()) {
            if (vote.getVoteAdds() != null) {
                voteAddDtos.add(voteAddMapper.voteAddToDto(vote.getVoteAdds()));
            }
        }
        return voteAddDtos;
    }

    @Override
    public List<VoteSkipDto> getAllVoteSkipByStream(Long streamId) throws Exception {
        Stream stream = streamRepository.findById(streamId).orElseThrow(Exception::new);
        List<VoteSkipDto> voteSkipDtos = new ArrayList<>();
        for (Vote vote : stream.getVote()) {
            if (vote.getVoteSkips() != null) {
                voteSkipDtos.add(voteSkipMapper.voteSkipToDto(vote.getVoteSkips()));
            }
        }
        return voteSkipDtos;
    }

    @Override
    public void addCategoriesToStream(Long streamId, List<Long> categoriesId) throws Exception {
        Stream stream = streamRepository.findById(streamId).orElseThrow(Exception::new);
        List<Category> categories = categoryRepository.findCategoriesIds(categoriesId);
        stream.setCategories(categories);
        streamRepository.save(stream);
        for (Category category : categories) {
            category.getStreams().add(stream);
            categoryRepository.save(category);
        }
    }

    @Override
    public void addUserToStream(Long streamId, Long userId) throws Exception {
        Stream stream = streamRepository.findById(streamId).orElseThrow(Exception::new);
        User user = userRepository.findById(userId).orElseThrow(Exception::new);
        stream.getUsers().add(user);
        streamRepository.save(stream);
        user.getStreams().add(stream);
        userRepository.save(user);
    }

    @Override
    public void addInsertVideoToStream(Long streamId, InsertVideosDto insVideoDto) throws Exception {
        Stream stream = streamRepository.findById(streamId).orElseThrow(Exception::new);
        InsertVideos insertVideos = insVideosMapper.dtoToInsertVideos(insVideoDto);
        Video video = videoRepository.findById(insVideoDto.getIdCurrentVideo()).orElseThrow(Exception::new);
        insertVideos.setCurrentVideo(video);
        insertVideos.setStream(stream);
        insVideosRepository.save(insertVideos);
    }

    @Override
    public List<StreamDto> findStreamsByTitle(String search) {
        List<Stream> streams = streamRepository.findByStreamTitleContainingIgnoreCase(search);
        return streamMapper.listStreamToListDto(streams);
    }

    @Override
    public List<VideoWithNumb> getFullPlaylistByStream(Long streamId) throws Exception {
        Stream stream = streamRepository.findById(streamId).orElseThrow(Exception::new);
        List<InsertVideos> insertVideos = insVideosRepository.findVideosByStream(streamId);
        List<VideoHasPlaylist> videos = stream.getPlaylist().getVideoHasPlaylists();
        FullPlaylistUtil fullPlaylistUtil = new FullPlaylistUtil(insertVideos, videos, videoMapper);
        return fullPlaylistUtil.getFullPlaylist();
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

    @Autowired
    public void setVideoRepository(VideoRepository videoRepository) {
        this.videoRepository = videoRepository;
    }

    @Autowired
    public void setVoteAddRepository(VoteAddRepository voteAddRepository) {
        this.voteAddRepository = voteAddRepository;
    }

    @Autowired
    public void setVoteSkipRepository(VoteSkipRepository voteSkipRepository) {
        this.voteSkipRepository = voteSkipRepository;
    }

    @Autowired
    public void setVoteSkipMapper(VoteSkipMapper voteSkipMapper) {
        this.voteSkipMapper = voteSkipMapper;
    }

    @Autowired
    public void setVoteAddMapper(VoteAddMapper voteAddMapper) {
        this.voteAddMapper = voteAddMapper;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setPlaylistRepository(PlaylistRepository playlistRepository) {
        this.playlistRepository = playlistRepository;
    }

    @Autowired
    public void setCategoryRepository(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Autowired
    public void setInsVideosRepository(InsertVideosRepository insVideosRepository) {
        this.insVideosRepository = insVideosRepository;
    }

    @Autowired
    public void setInsVideosMapper(InsertVideosMapper insVideosMapper) {
        this.insVideosMapper = insVideosMapper;
    }

    @Autowired
    public void setVideoMapper(VideoMapper videoMapper) {
        this.videoMapper = videoMapper;
    }

    @Autowired
    public void setMessageRepository(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Autowired
    public void setSimpMessagingTemplate(SimpMessagingTemplate simpMessagingTemplate) {
        this.simpMessagingTemplate = simpMessagingTemplate;
    }
}
