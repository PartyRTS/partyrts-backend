package stud.team.pwsbackend.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import stud.team.pwsbackend.domain.GlobalRole;
import stud.team.pwsbackend.dto.GlobalRoleDto;

import java.util.List;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
)
public interface GlobalRoleMapper {
    GlobalRole mapToEntity(GlobalRoleDto dto);

    void updateEntity(@MappingTarget GlobalRole entity, GlobalRoleDto dto);

    GlobalRoleDto mapToDto(GlobalRole entity);

    List<GlobalRoleDto> mapToDto(List<GlobalRole> entities);
}
