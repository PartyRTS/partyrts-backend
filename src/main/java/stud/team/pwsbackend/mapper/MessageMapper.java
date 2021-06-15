package stud.team.pwsbackend.mapper;

import org.mapstruct.*;
import stud.team.pwsbackend.domain.Message;
import stud.team.pwsbackend.dto.MessageDto;

import java.util.List;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
)
public interface MessageMapper {
    Message mapToEntity(MessageDto dto);

    void updateEntity(@MappingTarget Message entity, MessageDto dto);

    @Mappings({
            @Mapping(target="idUser", source="entity.user.idUser"),
            @Mapping(target="idStream", source="entity.stream.idStream")
    })
    MessageDto mapToDto(Message entity);

    List<MessageDto> mapToDto(List<Message> entities);
}
