package com.klu.config;

import com.klu.entity.Role;
import com.klu.entity.User;
import com.klu.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner initUsers(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (!userRepository.existsByUsername("admin")) {
                User admin = new User();
                admin.setUsername("admin");
                admin.setPassword(passwordEncoder.encode("admin123"));
                admin.setRole(Role.ADMIN);
                userRepository.save(admin);
            }

            if (!userRepository.existsByUsername("employee")) {
                User employee = new User();
                employee.setUsername("employee");
                employee.setPassword(passwordEncoder.encode("employee123"));
                employee.setRole(Role.EMPLOYEE);
                userRepository.save(employee);
            }
        };
    }
}
