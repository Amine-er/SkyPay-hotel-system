package ma.errabi.user.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.errabi.user.UserDTO;
import ma.errabi.user.domain.User;
import ma.errabi.user.mapper.UserMapper;
import ma.errabi.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
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

    @Transactional(readOnly = true)
    public List<UserDTO> getAllUsers() {
        log.info("Fetching all users from the database");
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public UserDTO getUserById(Long id) {
        log.info("Fetching user by ID: {}", id);
        return userRepository.findById(id)
                .map(userMapper::toDto)
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + id));
    }
}
