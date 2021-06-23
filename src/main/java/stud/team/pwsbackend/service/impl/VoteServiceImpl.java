package stud.team.pwsbackend.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import stud.team.pwsbackend.domain.*;
import stud.team.pwsbackend.dto.UserVoteDto;
import stud.team.pwsbackend.dto.VideoWithNumb;
import stud.team.pwsbackend.dto.VoteDto;
import stud.team.pwsbackend.mapper.UserVoteMapper;
import stud.team.pwsbackend.mapper.VoteMapper;
import stud.team.pwsbackend.repository.InsertVideosRepository;
import stud.team.pwsbackend.repository.UserRepository;
import stud.team.pwsbackend.repository.UserVoteRepository;
import stud.team.pwsbackend.repository.VoteRepository;
import stud.team.pwsbackend.service.StreamService;
import stud.team.pwsbackend.service.VoteService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Slf4j
public class VoteServiceImpl implements VoteService {

    private VoteRepository voteRepository;
    private InsertVideosRepository insRepository;
    private UserRepository userRepository;
    private UserVoteRepository userVoteRepository;
    private StreamService streamService;
    private VoteMapper voteMapper;
    private UserVoteMapper userVoteMapper;
    private SimpMessagingTemplate simpMessagingTemplate;

    @Override
    public List<VoteDto> getAllVotes() {
        List<Vote> votes = voteRepository.findAll();
        return voteMapper.listVoteToListDto(votes);
    }

    @Override
    public VoteDto getVoteById(Long voteId) {
        Optional<Vote> vote = voteRepository.findById(voteId);
        return vote.map(voteMapper::voteToDto).orElse(null);
    }


    @Override
    public void closeVoteById(Long voteId) throws Exception {
        Vote vote = voteRepository.findById(voteId).orElseThrow(Exception::new);
        if(!vote.getCloseVote()) {
            Stream stream = vote.getStream();
            int size = stream.getPlaylist().getVideoHasPlaylists().size() + stream.getInsertVideos().size();
            if (vote.getVoteSkips() != null) {
                if(getPercentPlusVotes(voteId) >= 0.49){
                    VoteSkip voteSkip = vote.getVoteSkips();
                    closeSkipVote(voteSkip.getNumberSkipVideo(), stream,size);
                }
                vote.setCloseVote(true);
            } else {
                if(getPercentPlusVotes(voteId) >= 0.49) {
                    VoteAdd voteAdd = vote.getVoteAdds();
                    closeAddVote(voteAdd.getNumberPrevVideo(), stream, voteAdd.getAddVideo(), size);
                }
                vote.setCloseVote(true);
            }
        }
    }

    @Override
    public double getPercentPlusVotes(Long voteId) throws Exception {
        Vote vote = voteRepository.findById(voteId).orElseThrow(Exception::new);
        int usersCount = vote.getUserVotes().size();
        Long plusCount = userVoteRepository.findCountPlusVotes(voteId);
        if(plusCount != null && usersCount > 0){
            return (double) plusCount/usersCount;
        }
        return 0;
    }

    @Override
    public UserVoteDto addUserVoteToVote(Long voteId, UserVoteDto userVoteDto) {
        Vote vote = voteRepository.findById(voteId).orElseThrow();
        User user = userRepository.findById(userVoteDto.getIdUser()).orElseThrow();
        UserVote userVote = new UserVote();
        userVote.setVotePlus(userVoteDto.getVotePlus());
        userVote.setVote(vote);
        userVote.setUser(user);
        userVote = userVoteRepository.save(userVote);
        return userVoteMapper.userVoteToDto(userVote);
    }

    public void closeSkipVote(Integer numberSkip,Stream stream,int size) throws Exception {
        List<VideoWithNumb> videoList = streamService.getFullPlaylistByStream(stream.getIdStream());
        for(int i = 0; i < videoList.size(); i++){
            if(i == numberSkip){
                InsertVideos insertVideo = new InsertVideos();
                insertVideo.setStream(stream);
                insertVideo.setNumberCurrentVideo(size + 1);
                Optional<InsertVideos> skipInsertVid = insRepository.findByNumberCurrentVideo(stream.getIdStream(),videoList.get(i).getNumberVideo());
                if(skipInsertVid.isPresent()){
                    skipInsertVid.get().setNumberPrevVideo(skipInsertVid.get().getNumberCurrentVideo());
                    insRepository.save(skipInsertVid.get());
                }
                if(i == 0){
                    insertVideo.setNumberPrevVideo(null);
                }else{
                    insertVideo.setNumberPrevVideo(videoList.get(i-1).getNumberVideo());
                    Optional<InsertVideos> firstInsertVid = insRepository.findByNumberCurrentVideo(stream.getIdStream(),videoList.get(i-1).getNumberVideo());
                    if(firstInsertVid.isPresent()){
                        firstInsertVid.get().setNumberNextVideo(insertVideo.getNumberCurrentVideo());
                        insRepository.save(firstInsertVid.get());
                    }
                }
                if (i + 1 < videoList.size()) {
                    insertVideo.setNumberNextVideo(videoList.get(i + 1).getNumberVideo());
                    Optional<InsertVideos> firstInsertVid = insRepository.findByNumberCurrentVideo(stream.getIdStream(), videoList.get(i + 1).getNumberVideo());
                    if (firstInsertVid.isPresent()) {
                        firstInsertVid.get().setNumberPrevVideo(insertVideo.getNumberCurrentVideo());
                        insRepository.save(firstInsertVid.get());
                    }
                    stream.setCurrentNumberVideo((long) (i + 1));
                } else {
                    stream.setActiveStream(false);
                }
                insRepository.save(insertVideo);

                log.info("asdsadsadasdjoipasjipowerrjgoiarejioerejogeooeg");
                log.info(String.valueOf(stream.getIdStream()));
                String topic = "/topic/streams/" + stream.getIdStream() + "/events";
                String message = "{\"type\": \"next\"}";
                simpMessagingTemplate.convertAndSend(topic, message);
            }
        }
    }

    public void closeAddVote(Integer numberPrev,Stream stream,Video video,int size) throws Exception {
        List<VideoWithNumb> videoList = streamService.getFullPlaylistByStream(stream.getIdStream());
        for(int i = 0; i < videoList.size(); i++){
            if(i == numberPrev){
                InsertVideos insertVideo = new InsertVideos();
                insertVideo.setCurrentVideo(video);
                insertVideo.setStream(stream);
                insertVideo.setNumberCurrentVideo(size + 1);
                insertVideo.setNumberPrevVideo(videoList.get(i).getNumberVideo());
                Optional<InsertVideos> prevInsertVid = insRepository.findByNumberPrevVideo(stream.getIdStream(),videoList.get(i).getNumberVideo());
                if(prevInsertVid.isPresent()){
                    prevInsertVid.get().setNumberPrevVideo(insertVideo.getNumberCurrentVideo());
                    insRepository.save(prevInsertVid.get());
                }
                Optional<InsertVideos> currInsertVid = insRepository.findByNumberCurrentVideo(stream.getIdStream(),videoList.get(i).getNumberVideo());
                if(currInsertVid.isPresent()){
                    currInsertVid.get().setNumberNextVideo(insertVideo.getNumberCurrentVideo());
                    insRepository.save(currInsertVid.get());
                }
                if(i + 1 < videoList.size()){
                    insertVideo.setNumberNextVideo(videoList.get(i+1).getNumberVideo());
                }
                insRepository.save(insertVideo);
            }
        }
    }

    @Autowired
    public void setVoteRepository(VoteRepository voteRepository) {
        this.voteRepository = voteRepository;
    }

    @Autowired
    public void setInsRepository(InsertVideosRepository insRepository) {
        this.insRepository = insRepository;
    }

    @Autowired
    public void setStreamService(StreamService streamService) {
        this.streamService = streamService;
    }

    @Autowired
    public void setVoteMapper(VoteMapper voteMapper) {
        this.voteMapper = voteMapper;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setUserVoteRepository(UserVoteRepository userVoteRepository) {
        this.userVoteRepository = userVoteRepository;
    }

    @Autowired
    public void setUserVoteMapper(UserVoteMapper userVoteMapper) {
        this.userVoteMapper = userVoteMapper;
    }

    @Autowired
    public void setSimpMessagingTemplate(SimpMessagingTemplate simpMessagingTemplate) {
        this.simpMessagingTemplate = simpMessagingTemplate;
    }
}
