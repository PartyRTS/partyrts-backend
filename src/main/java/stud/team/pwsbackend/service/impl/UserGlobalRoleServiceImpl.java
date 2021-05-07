package stud.team.pwsbackend.service.impl;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import stud.team.pwsbackend.dto.GlobalRoleDto;
import stud.team.pwsbackend.exception.globalrole.GlobalRoleNotFoundException;
import stud.team.pwsbackend.exception.user.UserNotFoundException;
import stud.team.pwsbackend.service.UserGlobalRoleService;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@Slf4j
public class UserGlobalRoleServiceImpl implements UserGlobalRoleService {

    @Override
    public List<GlobalRoleDto> getAllGlobalRoles(long userId) throws UserNotFoundException {
        return null;
    }

    @Override
    public void addGlobalRole(long userId, long globalRoleId) throws UserNotFoundException, GlobalRoleNotFoundException {

    }

    @Override
    public void deleteAllGlobalRoles(long userId) throws UserNotFoundException {

    }

    @Override
    public void deleteGlobalRole(long userId, long globalRoleId) throws UserNotFoundException, GlobalRoleNotFoundException {

    }
}
