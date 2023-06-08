package com.example.demo.services;

import com.example.demo.UserRepository;
import com.example.demo.dtos.UserDTO;
import com.example.demo.models.User;
import com.example.demo.utils.UserMapper;
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
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
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

    public void updateUserFromDto(UserDTO dto) {
        log.info("Обновление пользователя: {}", dto);

        User user = userRepository.findById(dto.getId())
                .orElseThrow(() -> new UserNotFoundException("Пользователь не найден: " + dto.toString()));
        userMapper.updateUserFromDto(dto, user);
        userRepository.save(user);
    }

}
