package stud.team.pwsbackend.mapper;

import org.mapstruct.*;
import stud.team.pwsbackend.domain.Playlist;
import stud.team.pwsbackend.dto.PlaylistDto;

import java.util.List;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
)
public interface PlaylistMapper {

    Playlist dtoToPlaylist(PlaylistDto playlistDto);

    @Mappings({
            @Mapping(target="idUser", source="playlist.user.idUser")
    })
    PlaylistDto playlistToDto(Playlist playlist);

    List<Playlist> listDtoToListPlaylist(List<PlaylistDto> playlistDtoList);

    List<PlaylistDto> listPlaylistToListDto(List<Playlist> playlistList);
}
