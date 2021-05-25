package stud.team.pwsbackend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import stud.team.pwsbackend.domain.Video;
import stud.team.pwsbackend.dto.VideoDto;
import stud.team.pwsbackend.mapper.VideoMapper;
import stud.team.pwsbackend.repository.VideoRepository;
import stud.team.pwsbackend.service.VideoService;

import java.util.List;
import java.util.Optional;

@Service
public class VideoServiceImpl implements VideoService {

    VideoRepository videoRepository;
    VideoMapper videoMapper;

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
    public VideoDto addVideo(VideoDto videoDto) {
        Video video = videoMapper.dtoToVideo(videoDto);
        video = videoRepository.save(video);
        return videoMapper.videoToDto(video);
    }

    @Override
    public void deleteVideoById(Long videoId) {
        videoRepository.deleteById(videoId);
    }

    @Autowired
    public void setVideoRepository(VideoRepository videoRepository) {
        this.videoRepository = videoRepository;
    }

    @Autowired
    public void setVideoMapper(VideoMapper videoMapper) {
        this.videoMapper = videoMapper;
    }
}
