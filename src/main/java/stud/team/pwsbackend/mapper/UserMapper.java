package stud.team.pwsbackend.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import stud.team.pwsbackend.domain.User;
import stud.team.pwsbackend.dto.UserDto;

import java.util.List;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
)
public interface UserMapper {
    User mapToEntity(UserDto dto);

    void updateEntity(@MappingTarget User entity, UserDto dto);

    UserDto mapToDto(User entity);

    List<UserDto> mapToDto(List<User> entities);
}
