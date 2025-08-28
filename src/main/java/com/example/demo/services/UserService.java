package com.example.demo.services;

import com.example.demo.dtos.UserDTO;
import com.example.demo.models.User;

import java.util.List;

/**
 * Сервис реализующий бизнес логику(В данном случае стандартный CRUD функционал)
 */
public interface UserService {
    User save(User user);

    User findById(Long id);

    List<User> findAll();

    void deleteById(Long id);

    void updateUserFromDto(UserDTO dto);
}
