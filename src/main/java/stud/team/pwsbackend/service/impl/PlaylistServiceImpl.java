package stud.team.pwsbackend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import stud.team.pwsbackend.domain.Category;
import stud.team.pwsbackend.domain.Playlist;
import stud.team.pwsbackend.dto.PlaylistDto;
import stud.team.pwsbackend.mapper.PlaylistMapper;
import stud.team.pwsbackend.repository.PlaylistRepository;
import stud.team.pwsbackend.service.PlaylistService;

import java.util.List;
import java.util.Optional;

@Service
public class PlaylistServiceImpl implements PlaylistService {

    PlaylistRepository playlistRepository;
    PlaylistMapper playlistMapper;

    @Override
    public List<PlaylistDto> getAllPlaylist() {
        List<Playlist> playlists = playlistRepository.findAll();
        return playlistMapper.listPlaylistToListDto(playlists);
    }

    @Override
    public void deleteAllPlaylist() {
        playlistRepository.deleteAll();
    }

    @Override
    public PlaylistDto getPlaylistById(Long playlistId) {
        Optional<Playlist> playlist = playlistRepository.findById(playlistId);
        return playlist.map(playlistMapper::playlistToDto).orElse(null);
    }

    @Override
    public PlaylistDto addPlaylist(PlaylistDto playlistDto) {
        Playlist playlist = playlistMapper.dtoToPlaylist(playlistDto);
        playlist = playlistRepository.save(playlist);
        return playlistMapper.playlistToDto(playlist);
    }

    @Override
    public void deletePlaylistById(Long playlistId) {
        playlistRepository.deleteById(playlistId);
    }

    @Autowired
    public void setPlaylistRepository(PlaylistRepository playlistRepository) {
        this.playlistRepository = playlistRepository;
    }

    @Autowired
    public void setPlaylistMapper(PlaylistMapper playlistMapper) {
        this.playlistMapper = playlistMapper;
    }
}