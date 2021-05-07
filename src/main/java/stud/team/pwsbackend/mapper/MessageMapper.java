package stud.team.pwsbackend.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import stud.team.pwsbackend.dto.MessageDto;
import stud.team.pwsbackend.entity.Message;

import java.util.List;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
)
public interface MessageMapper {
    Message mapToEntity(MessageDto dto);

    void updateEntity(@MappingTarget Message entity, MessageDto dto);

    MessageDto mapToDto(Message entity);

    List<MessageDto> mapToDto(List<Message> entities);
}
