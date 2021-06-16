package stud.team.pwsbackend.controller.http;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import stud.team.pwsbackend.dto.VideoDto;
import stud.team.pwsbackend.service.VideoService;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/videos",
        produces = "application/json")
@Slf4j
public class VideoController {

    private final VideoService videoService;

    public VideoController(VideoService videoService) {
        this.videoService = videoService;
    }

    @GetMapping
    public List<VideoDto> getAllVideo() {
        return videoService.getAllVideo();
    }

    @DeleteMapping
    public void deleteAllVideo() {
        videoService.deleteAllVideo();
    }

    @GetMapping("/{videoId}")
    public VideoDto getVideoById(@PathVariable Long videoId) {
        return videoService.getVideoById(videoId);
    }

    @PostMapping
    public VideoDto addVideo(@RequestBody VideoDto videoDto) throws Exception {
        return videoService.addVideo(videoDto);
    }

    @DeleteMapping("/{videoId}")
    public void deleteVideoById(@PathVariable Long videoId) {
        videoService.deleteVideoById(videoId);
    }
}
