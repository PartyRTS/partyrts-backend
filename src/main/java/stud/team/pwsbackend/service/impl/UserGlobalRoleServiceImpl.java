package stud.team.pwsbackend.service.impl;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import stud.team.pwsbackend.dto.GlobalRoleDto;
import stud.team.pwsbackend.mapper.GlobalRoleMapper;
import stud.team.pwsbackend.repository.UserRepository;
import stud.team.pwsbackend.service.UserGlobalRoleService;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@Slf4j
public class UserGlobalRoleServiceImpl implements UserGlobalRoleService {

    private final UserRepository userRepository;
    private final GlobalRoleMapper globalRoleMapper;

    public UserGlobalRoleServiceImpl(UserRepository userRepository, GlobalRoleMapper globalRoleMapper) {
        this.userRepository = userRepository;
        this.globalRoleMapper = globalRoleMapper;
    }

    @Override
    public List<GlobalRoleDto> getAllGlobalRoles(long userId) {
        var user = userRepository.findById(userId).orElseThrow();
        var roles = user.getGlobalRoles();
        return globalRoleMapper.mapToDto(roles);
    }

    @Override
    public void addGlobalRole(long userId, long globalRoleId) {

    }

    @Override
    public void deleteAllGlobalRoles(long userId) {

    }

    @Override
    public void deleteGlobalRole(long userId, long globalRoleId) {

    }
}
