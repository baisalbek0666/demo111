package peaksoft.java15.demo111.service.impl;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import peaksoft.java15.demo111.dto.request.UserRequest;
import peaksoft.java15.demo111.dto.response.UserResponse;
import peaksoft.java15.demo111.entity.User;
import peaksoft.java15.demo111.enums.Role;
import peaksoft.java15.demo111.exceptions.BadRequestException;
import peaksoft.java15.demo111.exceptions.NotFoundException;
import peaksoft.java15.demo111.repository.UserRepository;
import peaksoft.java15.demo111.service.EmailService;
import peaksoft.java15.demo111.service.UserService;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final EmailService emailService;

    private UserResponse mapToResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .role(user.getRole())
                .build();


    }

    @Override
    public UserResponse createUser(UserRequest request) {
        User user = User.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .password(request.password())
                .role(Role.USER)
                .build();
        User savedUser = userRepository.save(user);

        emailService.sendEmail(
                user.getEmail(),
                "Добро пожаловать!",
                "Здравствуйте, %s! Ваш аккаунт успешно создан.".formatted(user.getFirstName())
        );

        return mapToResponse(savedUser);
    }

    @Override
    @Cacheable(value ="users")
    public List<UserResponse> getAllUser() {
        return userRepository.findAll().stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public UserResponse getUserById(Long id) {
        return userRepository.findById(id)
                .map(this::mapToResponse)
                .orElseThrow(() -> new NotFoundException("User with id %d not found".formatted(id)));
    }

    @Override
    public UserResponse updateUser(Long id, UserRequest request) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User with id %d not found".formatted(id)));

        user.setFirstName(request.firstName());
        user.setLastName(request.lastName());
        user.setEmail(request.email());
        user.setPassword(request.password());

        return mapToResponse(userRepository.save(user));
    }

    @Override
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new BadRequestException("User with id %d not found".formatted(id));
        }
        userRepository.deleteById(id);
    }
}
