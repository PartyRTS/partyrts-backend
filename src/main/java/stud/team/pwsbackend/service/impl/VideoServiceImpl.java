package stud.team.pwsbackend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import stud.team.pwsbackend.domain.User;
import stud.team.pwsbackend.domain.Video;
import stud.team.pwsbackend.dto.VideoDto;
import stud.team.pwsbackend.exception.user.UserNotFoundException;
import stud.team.pwsbackend.mapper.VideoMapper;
import stud.team.pwsbackend.repository.UserRepository;
import stud.team.pwsbackend.repository.VideoRepository;
import stud.team.pwsbackend.service.VideoService;

import java.util.List;
import java.util.Optional;

@Service
public class VideoServiceImpl implements VideoService {

    private VideoRepository videoRepository;
    private UserRepository userRepository;
    private VideoMapper videoMapper;

    @Override
    public List<VideoDto> getAllVideo() {
        List<Video> videos = videoRepository.findAll();
        return videoMapper.listVideoToListDto(videos);
    }

    @Override
    public void deleteAllVideo() {
        videoRepository.deleteAll();
    }

    @Override
    public VideoDto getVideoById(Long videoId) {
        Optional<Video> video = videoRepository.findById(videoId);
        return video.map(videoMapper::videoToDto).orElse(null);
    }

    @Override
    public VideoDto addVideo(VideoDto videoDto) throws Exception {
        Video video = videoMapper.dtoToVideo(videoDto);
        User user = userRepository.findById(videoDto.getIdUser()).orElseThrow(Exception::new);
        video.setUser(user);
        video = videoRepository.save(video);
        return videoMapper.videoToDto(video);
    }

    @Override
    public void deleteVideoById(Long videoId) {
        videoRepository.deleteById(videoId);
    }

    @Override
    public List<VideoDto> getAllVideoByUser(Long userId) throws UserNotFoundException {
        User user = userRepository.findById(userId).orElseThrow();
        return videoMapper.listVideoToListDto(user.getUserVideos());
    }

    @Autowired
    public void setVideoRepository(VideoRepository videoRepository) {
        this.videoRepository = videoRepository;
    }

    @Autowired
    public void setVideoMapper(VideoMapper videoMapper) {
        this.videoMapper = videoMapper;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
