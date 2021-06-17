package stud.team.pwsbackend.service;

import stud.team.pwsbackend.dto.VideoDto;
import stud.team.pwsbackend.exception.user.UserNotFoundException;

import java.util.List;

public interface VideoService {

    List<VideoDto> getAllVideo();

    void deleteAllVideo();

    VideoDto getVideoById(Long videoId);

    VideoDto addVideo(VideoDto videoDto) throws Exception;

    void deleteVideoById(Long videoId);

    List<VideoDto> getAllVideoByUser(Long userId) throws UserNotFoundException;
}
