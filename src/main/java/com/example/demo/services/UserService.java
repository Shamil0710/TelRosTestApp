package com.example.demo.services;

import com.example.demo.UserRepository;
import com.example.demo.models.User;
import exceptions.UserNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Сервисный класс реализующий бизне логику
 */
@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id.toString()));
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

}
