package stud.team.pwsbackend.service.impl;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import stud.team.pwsbackend.dto.GlobalRoleDto;
import stud.team.pwsbackend.exception.globalrole.GlobalRoleNotFoundException;
import stud.team.pwsbackend.service.GlobalRoleService;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@Slf4j
public class GlobalRoleServiceImpl implements GlobalRoleService {

    @Override
    public List<GlobalRoleDto> getAllGlobalRoles() {
        return null;
    }

    @Override
    public GlobalRoleDto addGlobalRole(GlobalRoleDto globalRole) {
        return null;
    }

    @Override
    public void deleteAllGlobalRoles() {

    }

    @Override
    public GlobalRoleDto getGlobalRole(long userId) throws GlobalRoleNotFoundException {
        return null;
    }

    @Override
    public void updateGlobalRole(long userId, GlobalRoleDto globalRole) throws GlobalRoleNotFoundException {

    }

    @Override
    public void deleteGlobalRole(long userId) throws GlobalRoleNotFoundException {

    }
}
