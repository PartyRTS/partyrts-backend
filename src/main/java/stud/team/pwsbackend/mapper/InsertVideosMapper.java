package stud.team.pwsbackend.mapper;

import org.mapstruct.*;
import stud.team.pwsbackend.domain.InsertVideos;
import stud.team.pwsbackend.dto.InsertVideosDto;

import java.util.List;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
)
public interface InsertVideosMapper {

    InsertVideos dtoToInsertVideos(InsertVideosDto insertVideosDto);

    @Mappings({
            @Mapping(target="idCurrentVideo", source="insertVideos.currentVideo.idVideo"),
            @Mapping(target="idStream", source="insertVideos.stream.idStream")
    })
    InsertVideosDto insertVideosToDto(InsertVideos insertVideos);

    List<InsertVideos> listDtoToListInsertVideos(List<InsertVideosDto> insertVideosDtoList);

    List<InsertVideosDto> listInsertVideosToListDto(List<InsertVideos> insertVideosList);
}
