package ma.errabi.user.services;

import lombok.RequiredArgsConstructor;
import ma.errabi.user.UserDTO;
import ma.errabi.user.domain.User;
import ma.errabi.user.mapper.UserMapper;
import ma.errabi.user.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final KeycloakAdminClientService keycloakAdminClientService;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserDTO createUser(UserDTO userDto) {
        if (userDto == null) {
            throw new IllegalArgumentException("UserDto cannot be null");
        }
        if (userRepository.existsByUsername(userDto.getUsername())) {
            throw new RuntimeException("User with username " + userDto.getUsername() + " already exists");
        }
        User user = userMapper.toEntity(userDto);
        User savedUser = userRepository.save(user);
        keycloakAdminClientService.createUserInKeycloak(savedUser, savedUser.getPassword());

        return userMapper.toDto(savedUser);
    }
}
