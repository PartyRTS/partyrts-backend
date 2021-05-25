package stud.team.pwsbackend.service;

import stud.team.pwsbackend.dto.PlaylistDto;

import java.util.List;

public interface PlaylistService {

    List<PlaylistDto> getAllPlaylist();

    void deleteAllPlaylist();

    PlaylistDto getPlaylistById(Long playlistId);

    PlaylistDto addPlaylist(PlaylistDto playlistDto);

    void deletePlaylistById(Long playlistId);
}
