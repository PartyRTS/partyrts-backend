package stud.team.pwsbackend.service;

import stud.team.pwsbackend.dto.VideoDto;

import java.util.List;

public interface VideoService {

    List<VideoDto> getAllVideo();

    void deleteAllVideo();

    VideoDto getVideoById(Long videoId);

    VideoDto addVideo(VideoDto videoDto);

    void deleteVideoById(Long videoId);
}
