package stud.team.pwsbackend.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import stud.team.pwsbackend.domain.InsertVideos;
import stud.team.pwsbackend.dto.InsertVideosDto;

import java.util.List;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
)
public interface InsertVideosMapper {

    InsertVideos dtoToInsertVideos(InsertVideosDto insertVideosDto);

    InsertVideosDto insertVideosToDto(InsertVideos insertVideos);

    List<InsertVideos> listDtoToListInsertVideos(List<InsertVideosDto> insertVideosDtoList);

    List<InsertVideosDto> listInsertVideosToListDto(List<InsertVideos> insertVideosList);
}
