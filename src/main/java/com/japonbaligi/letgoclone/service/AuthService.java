package com.japonbaligi.letgoclone.service;

import com.japonbaligi.letgoclone.entity.User;
import com.japonbaligi.letgoclone.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    private static final String DEMO_USERNAME = "demo";
    private static final String DEMO_PASSWORD = "demo123";
    private static final String DEMO_EMAIL = "demo@takikazan.com";
    private static final String DEMO_FULL_NAME = "Demo Kullanıcı";

    public boolean authenticate(String username, String password) {
        if (DEMO_USERNAME.equals(username) && DEMO_PASSWORD.equals(password)) {
            return true;
        }

        Optional<User> user = userRepository.findByUsername(username);
        return user.isPresent() && user.get().getPassword().equals(password);
    }

    public User getDemoUser() {
        return new User(DEMO_USERNAME, DEMO_PASSWORD, DEMO_EMAIL, DEMO_FULL_NAME);
    }

    public User getUserByUsername(String username) {
        if (DEMO_USERNAME.equals(username)) {
            Optional<User> existingDemoUser = userRepository.findByUsername(DEMO_USERNAME);
            if (existingDemoUser.isPresent()) {
                return existingDemoUser.get();
            } else {
                User demoUser = getDemoUser();
                return userRepository.save(demoUser);
            }
        }

        return userRepository.findByUsername(username).orElse(null);
    }

    public User createUser(String username, String password, String email, String fullName) {
        User user = new User(username, password, email, fullName);
        return userRepository.save(user);
    }

    public boolean userExists(String username) {
        if (DEMO_USERNAME.equals(username)) {
            return true;
        }
        return userRepository.existsByUsername(username);
    }

    public boolean emailExists(String email) {
        if (DEMO_EMAIL.equals(email)) {
            return true;
        }
        return userRepository.existsByEmail(email);
    }
}
