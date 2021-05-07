package stud.team.pwsbackend.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import stud.team.pwsbackend.domain.VideoHasPlaylist;
import stud.team.pwsbackend.dto.VideoHasPlaylistDto;

import java.util.List;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
)
public interface VideoHasPlaylistMapper {

    VideoHasPlaylist dtoToVHP(VideoHasPlaylistDto videoHasPlaylistDto);

    VideoHasPlaylistDto vHPToDto(VideoHasPlaylist videoHasPlaylist);

    List<VideoHasPlaylist> listDtoToListVHP(List<VideoHasPlaylistDto> videoHasPlaylistDtoList);

    List<VideoHasPlaylistDto> listVHPToListDto(List<VideoHasPlaylist> videoHasPlaylists);
}
