package stud.team.pwsbackend.service.impl;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import stud.team.pwsbackend.domain.Password;
import stud.team.pwsbackend.domain.User;
import stud.team.pwsbackend.dto.LoginRequestDto;
import stud.team.pwsbackend.dto.NewUserDto;
import stud.team.pwsbackend.dto.UserDto;
import stud.team.pwsbackend.exception.message.IncorrectCredentialsException;
import stud.team.pwsbackend.exception.user.UserNotFoundException;
import stud.team.pwsbackend.mapper.UserMapper;
import stud.team.pwsbackend.repository.GlobalRoleRepository;
import stud.team.pwsbackend.repository.UserRepository;
import stud.team.pwsbackend.service.UserService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Service
@Transactional
@Slf4j
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private GlobalRoleRepository globalRoleRepository;
    private UserMapper userMapper;
    private PasswordEncoder passwordEncoder;


    public List<UserDto> getAllUsers() {
        var users = userRepository.findAll();
        return userMapper.mapToDto(users);
    }

    public UserDto addUser(NewUserDto userDto) {
        User user = userMapper.mapToEntity(userDto);
        Password userPassword = new Password();

        String passwordHash = passwordEncoder.encode(userDto.getPassword());
        userPassword.setPasswordHash(passwordHash);

        user.setPassword(userPassword);
        userPassword.setUser(user);

        user = userRepository.save(user);
        return userMapper.mapToDto(user);
    }

    public void deleteAllUsers() {
        userRepository.deleteAll();
    }

    public UserDto getUser(long userId) throws UserNotFoundException {
        User user = findUserById(userId);
        return userMapper.mapToDto(user);
    }

    public void updateUser(long userId, UserDto userDto) throws UserNotFoundException {
        User user = findUserById(userId);
        userMapper.updateEntity(user, userDto);
    }

    public void deleteUser(long userId) {
        userRepository.deleteById(userId);
    }

    private User findUserById(long userId) throws UserNotFoundException {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
    }

    public UserDto login(LoginRequestDto loginRequestDto) throws IncorrectCredentialsException {
        String email = loginRequestDto.getEmail();
        String password = loginRequestDto.getPassword();

        var user = userRepository.findByEmail(email).orElseThrow(IncorrectCredentialsException::new);
        var userId = user.getIdUser();

        String hash = user.getPassword().getPasswordHash();

        if (passwordEncoder.matches(password, hash)) {
            return userMapper.mapToDto(user);
        }
        throw new IncorrectCredentialsException();
    }

    public UserDto register(NewUserDto reguserDto) {
        UserDto userDto = addUser(reguserDto);
        User user = userRepository.findById(userDto.getIdUser())
                .orElseThrow(IllegalStateException::new);

        var userRole = globalRoleRepository
                .findByTitle("USER")
                .orElseThrow(IllegalStateException::new);

        user.setGlobalRoles(Set.of(userRole));
        return userMapper.mapToDto(user);
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    public void setGlobalRoleRepository(GlobalRoleRepository globalRoleRepository) {
        this.globalRoleRepository = globalRoleRepository;
    }
}
