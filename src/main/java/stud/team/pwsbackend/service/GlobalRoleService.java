package stud.team.pwsbackend.service;

import stud.team.pwsbackend.dto.GlobalRoleDto;
import stud.team.pwsbackend.exception.globalrole.GlobalRoleNotFoundException;

import java.util.List;

public interface GlobalRoleService {
    List<GlobalRoleDto> getAllGlobalRoles();

    GlobalRoleDto addGlobalRole(GlobalRoleDto globalRole);

    void deleteAllGlobalRoles();

    GlobalRoleDto getGlobalRole(long userId) throws GlobalRoleNotFoundException;

    void updateGlobalRole(long userId, GlobalRoleDto globalRole) throws GlobalRoleNotFoundException;

    void deleteGlobalRole(long userId) throws GlobalRoleNotFoundException;
}
