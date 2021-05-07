package stud.team.pwsbackend.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import stud.team.pwsbackend.domain.Playlist;
import stud.team.pwsbackend.dto.PlaylistDto;

import java.util.List;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
)
public interface PlaylistMapper {

    Playlist dtoToPlaylist(PlaylistDto playlistDto);

    PlaylistDto playlistToDto(Playlist playlist);

    List<Playlist> listDtoToListPlaylist(List<PlaylistDto> playlistDtoList);

    List<PlaylistDto> listPlaylistToListDto(List<Playlist> playlistList);
}
