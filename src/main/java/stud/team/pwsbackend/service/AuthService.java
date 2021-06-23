package stud.team.pwsbackend.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import stud.team.pwsbackend.dto.GlobalRoleDto;
import stud.team.pwsbackend.dto.LoginRequestDto;
import stud.team.pwsbackend.dto.NewUserDto;
import stud.team.pwsbackend.dto.UserDto;
import stud.team.pwsbackend.exception.user.UserNotFoundException;
import stud.team.pwsbackend.security.SimpleAuthentication;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AuthService {
    private final UserService userService;
    private final UserGlobalRoleService userGlobalRoleService;

    public AuthService(UserService userService, UserGlobalRoleService userGlobalRoleService) {
        this.userService = userService;
        this.userGlobalRoleService = userGlobalRoleService;
    }

    public Long login(LoginRequestDto loginRequestDto) throws Exception {
        log.debug("try login user");
        UserDto userDto = userService.login(loginRequestDto);

        long userId = userDto.getIdUser();
        log.debug("user id: " + userId);

        if (userDto.getBanned()) {
            log.debug("user with id: {} banned!", userId);
            throw new Exception("user is banned");
        }

        List<GlobalRoleDto> globalRoles = userGlobalRoleService.getAllGlobalRoles(userId);
        List<GrantedAuthority> authorities = toGrantedAuthorities(globalRoles);
        log.debug("authorities: " + authorities);
        Authentication authentication = new SimpleAuthentication(userId, authorities);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        return userDto.getIdUser();
    }

    public Optional<Long> register(NewUserDto newUserDto) throws UserNotFoundException {
        log.debug("try register user");
        UserDto userDto = userService.register(newUserDto);

        List<GlobalRoleDto> globalRoles = userGlobalRoleService.getAllGlobalRoles(userDto.getIdUser());
        List<GrantedAuthority> authorities = toGrantedAuthorities(globalRoles);
        log.debug("authorities: " + authorities);

        Authentication authentication = new SimpleAuthentication(userDto.getIdUser(), authorities);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return Optional.of(userDto.getIdUser());
    }


    private List<GrantedAuthority> toGrantedAuthorities(List<GlobalRoleDto> globalRoleDtos) {
        return globalRoleDtos.stream()
                .map(globalRoleDto -> new SimpleGrantedAuthority("ROLE_" + globalRoleDto.getTitle()))
                .collect(Collectors.toList());
    }
}
