package peaksoft.java15.demo111.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import peaksoft.java15.demo111.entity.User;
import peaksoft.java15.demo111.enums.Role;
import peaksoft.java15.demo111.repository.UserRepository;

@Configuration
@RequiredArgsConstructor
public class DataInitializer {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Bean
    public CommandLineRunner dataLoader() {
        return args -> {
            if (!userRepository.existsByEmail("admin@gmail.com")) {
                User user = new User();
                user.setEmail("admin@gmail.com");
                user.setPassword(passwordEncoder.encode("admin123"));
                user.setFirstName("Admin");
                user.setLastName("admin");
                user.setRole(Role.ADMIN);
                userRepository.save(user);
            }
        };
    }
}
