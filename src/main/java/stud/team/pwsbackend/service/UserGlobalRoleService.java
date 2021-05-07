package stud.team.pwsbackend.service;

import org.springframework.stereotype.Service;
import stud.team.pwsbackend.dto.GlobalRoleDto;
import stud.team.pwsbackend.exception.globalrole.GlobalRoleNotFoundException;
import stud.team.pwsbackend.exception.user.UserNotFoundException;

import java.util.List;

@Service
public interface UserGlobalRoleService {
    List<GlobalRoleDto> getAllGlobalRoles(long userId) throws UserNotFoundException;

    void addGlobalRole(long userId, long globalRoleId) throws UserNotFoundException, GlobalRoleNotFoundException;

    void deleteAllGlobalRoles(long userId) throws UserNotFoundException;

    void deleteGlobalRole(long userId, long globalRoleId) throws UserNotFoundException, GlobalRoleNotFoundException;
}
