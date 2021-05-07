package stud.team.pwsbackend.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import stud.team.pwsbackend.domain.Video;
import stud.team.pwsbackend.dto.VideoDto;

import java.util.List;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
)
public interface VideoMapper {

    Video dtoToVideo(VideoDto streamDto);

    VideoDto videoToDto(Video video);

    List<Video> listDtoToListVideo(List<VideoDto> videoDtoList);

    List<VideoDto> listVideoToListDto(List<Video> videoList);
}
