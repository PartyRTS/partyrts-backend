package stud.team.pwsbackend.mapper;

import org.mapstruct.*;
import stud.team.pwsbackend.domain.Video;
import stud.team.pwsbackend.dto.VideoDto;
import stud.team.pwsbackend.dto.VideoWithNumb;

import java.util.List;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
)
public interface VideoMapper {

    Video dtoToVideo(VideoDto streamDto);

    @Mappings({
            @Mapping(target="idUser", source="video.user.idUser")
    })
    VideoDto videoToDto(Video video);

    VideoWithNumb videoToNumbVideo(Video video);

    VideoDto numbVideoToVideo(VideoWithNumb videoWithNumb);

    List<VideoDto> numbVideoToVideo(List<VideoWithNumb> videoWithNumb);

    List<Video> listDtoToListVideo(List<VideoDto> videoDtoList);

    List<VideoDto> listVideoToListDto(List<Video> videoList);
}
