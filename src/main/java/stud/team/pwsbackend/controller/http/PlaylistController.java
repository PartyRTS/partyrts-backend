package stud.team.pwsbackend.controller.http;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import stud.team.pwsbackend.dto.PlaylistDto;
import stud.team.pwsbackend.dto.VideoDto;
import stud.team.pwsbackend.service.PlaylistService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/playlists",
        produces = "application/json")
@Slf4j
public class PlaylistController {

    private final PlaylistService playlistService;

    public PlaylistController(PlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    @GetMapping
    public List<PlaylistDto> getAllPlaylist() {
        return playlistService.getAllPlaylist();
    }

    @DeleteMapping
    public void deleteAllPlaylist() {
        playlistService.deleteAllPlaylist();
    }

    @GetMapping("/{playlistId}")
    public PlaylistDto getPlaylistById(@PathVariable Long playlistId) {
        return playlistService.getPlaylistById(playlistId);
    }

    @PostMapping
    public PlaylistDto addPlaylist(@Valid @RequestBody PlaylistDto playlistDto) {
        return playlistService.addPlaylist(playlistDto);
    }

    @DeleteMapping("/{playlistId}")
    public void deletePlaylistById(@PathVariable Long playlistId) {
        playlistService.deletePlaylistById(playlistId);
    }

    @GetMapping("/{playlistId}/videos")
    public List<VideoDto> getVideosByPlaylist(@PathVariable Long playlistId) throws Exception {
        return playlistService.getVideosByPlaylist(playlistId);
    }

    @PostMapping("/{playlistId}/addVideo")
    public void addVideoToPlaylist(@PathVariable Long playlistId,@RequestBody Long videoId) throws Exception {
        playlistService.addVideoToPlaylist(playlistId,videoId);
    }
}
