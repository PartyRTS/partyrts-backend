package stud.team.pwsbackend.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import stud.team.pwsbackend.domain.Stream;
import stud.team.pwsbackend.dto.StreamDto;

import java.util.List;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
)
public interface StreamMapper {

    Stream dtoToStream(StreamDto streamDto);

    StreamDto streamToDto(Stream stream);

    List<Stream> listDtoToListStream(List<StreamDto> streamDtoList);

    List<StreamDto> listStreamToListDto(List<Stream> streamList);
}
