package com.example.demo.services;

import com.example.demo.UserRepository;
import com.example.demo.models.User;
import exceptions.UserNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Сервисный класс реализующий бизне логику
 */
@Service
public class UserService {
    private static final Logger log = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User save(User user) {
        log.info("Сохранение пользователя {}", user);

        return userRepository.save(user);
    }

    public User findById(Long id) {
        log.info("Получение пользователя по id: {}", id);

        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id.toString()));
    }

    public List<User> findAll() {
        log.info("Получения списка всех пользователей");

        return userRepository.findAll();
    }

    public void deleteById(Long id) {
        log.info("Удаление пользователя по id: {}", id);

        userRepository.deleteById(id);
    }

}
