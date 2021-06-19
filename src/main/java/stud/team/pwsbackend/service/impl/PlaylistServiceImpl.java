package stud.team.pwsbackend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import stud.team.pwsbackend.domain.Playlist;
import stud.team.pwsbackend.domain.User;
import stud.team.pwsbackend.domain.Video;
import stud.team.pwsbackend.domain.VideoHasPlaylist;
import stud.team.pwsbackend.dto.PlaylistDto;
import stud.team.pwsbackend.dto.VideoDto;
import stud.team.pwsbackend.exception.user.UserNotFoundException;
import stud.team.pwsbackend.mapper.PlaylistMapper;
import stud.team.pwsbackend.mapper.VideoMapper;
import stud.team.pwsbackend.repository.PlaylistRepository;
import stud.team.pwsbackend.repository.UserRepository;
import stud.team.pwsbackend.repository.VideoHasPlaylistRepository;
import stud.team.pwsbackend.repository.VideoRepository;
import stud.team.pwsbackend.service.PlaylistService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PlaylistServiceImpl implements PlaylistService {

    private PlaylistRepository playlistRepository;
    private UserRepository userRepository;
    private VideoRepository videoRepository;
    private VideoHasPlaylistRepository videoHPRepository;
    private PlaylistMapper playlistMapper;
    private VideoMapper videoMapper;

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
    public PlaylistDto addPlaylist(PlaylistDto playlistDto) throws UserNotFoundException {
        Playlist playlist = playlistMapper.dtoToPlaylist(playlistDto);
        User user = userRepository.findById(playlistDto.getIdUser()).orElseThrow();
        playlist.setUser(user);
        playlist = playlistRepository.save(playlist);
        return playlistMapper.playlistToDto(playlist);
    }

    @Override
    public void deletePlaylistById(Long playlistId) {
        playlistRepository.deleteById(playlistId);
    }

    @Override
    public List<VideoDto> getVideosByPlaylist(Long playlistId) throws Exception {
        Playlist playlist = playlistRepository.findById(playlistId).orElseThrow();
        List<VideoHasPlaylist> videoList = playlist.getVideoHasPlaylists();
        List<Video> result = new ArrayList<>();
        for(VideoHasPlaylist vhp : videoList){
            result.add(vhp.getVideo());
        }
        return videoMapper.listVideoToListDto(result);
    }

    @Override
    public void addVideoToPlaylist(Long playlistId, Long videoId) throws Exception {
        Playlist playlist = playlistRepository.findById(playlistId).orElseThrow();
        Video video = videoRepository.findById(videoId).orElseThrow();
        int number = playlist.getVideoHasPlaylists().size();
        VideoHasPlaylist videoHasPlaylist = new VideoHasPlaylist();
        videoHasPlaylist.setVideo(video);
        videoHasPlaylist.setNumberVideo(number + 1);
        videoHasPlaylist.setPlaylist(playlist);
        videoHPRepository.save(videoHasPlaylist);
    }

    @Override
    public List<PlaylistDto> getAllPlaylistsByUser(Long userId) throws UserNotFoundException {
        User user = userRepository.findById(userId).orElseThrow();
        return playlistMapper.listPlaylistToListDto(user.getUserPlaylists());
    }

    @Autowired
    public void setPlaylistRepository(PlaylistRepository playlistRepository) {
        this.playlistRepository = playlistRepository;
    }

    @Autowired
    public void setPlaylistMapper(PlaylistMapper playlistMapper) {
        this.playlistMapper = playlistMapper;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setVideoMapper(VideoMapper videoMapper) {
        this.videoMapper = videoMapper;
    }

    @Autowired
    public void setVideoRepository(VideoRepository videoRepository) {
        this.videoRepository = videoRepository;
    }

    @Autowired
    public void setVideoHPRepository(VideoHasPlaylistRepository videoHPRepository) {
        this.videoHPRepository = videoHPRepository;
    }
}
