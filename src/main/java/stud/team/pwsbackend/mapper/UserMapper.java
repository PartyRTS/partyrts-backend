package stud.team.pwsbackend.mapper;

import org.mapstruct.*;
import stud.team.pwsbackend.domain.User;
import stud.team.pwsbackend.dto.NewUserDto;
import stud.team.pwsbackend.dto.UserDto;

import java.util.List;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
)
public interface UserMapper {
    User mapToEntity(UserDto dto);

    @Mapping(target = "password", ignore = true)
    User mapToEntity(NewUserDto dto);

    void updateEntity(@MappingTarget User entity, UserDto dto);

    UserDto mapToDto(User entity);

    List<UserDto> mapToDto(List<User> entities);
}
