package com.example.demo.rest;

import com.example.demo.dtos.UserDTO;
import com.example.demo.models.User;
import com.example.demo.services.UserService;
import com.example.demo.utils.UserMapper;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Класс контроллер отвечающий за ответы api
 */
@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @Operation(summary = "Finds a user by ID.")
    @GetMapping("/{id}")
    public UserDTO findById(@PathVariable Long id) {
        User user = userService.findById(id);
        return userMapper.toUserDTO(user);
    }


    @Operation(summary = "Finds all users.")
    @GetMapping
    public List<UserDTO> findAll() {
        List<User> users = userService.findAll();
        return userMapper.toUserDTOs(users);
    }

    @Operation(summary = "Creates a new user.")
    @PostMapping
    public UserDTO create(@RequestBody UserDTO userDTO) {
        User user = userMapper.toUser(userDTO);
        user = userService.save(user);
        return userMapper.toUserDTO(user);
    }

    @Operation(summary = "Updates an existing user.")
    @PutMapping("/{id}")
    public UserDTO update(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        User user = userService.findById(id);
        user.setEmail(userDTO.getEmail());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setMiddleName(userDTO.getMiddleName());
        user.setBirthDate(userDTO.getBirthDate());
        user.setPhone(userDTO.getPhone());
        user.setPhoto(userDTO.getPhoto());
        user = userService.save(user);
        return userMapper.toUserDTO(user);
    }

    @Operation(summary = "Deletes a user by ID.")
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        userService.deleteById(id);
    }

    //Служебные классы, при желании можно вынести отдельно  реалзовать как статичные методы
    @Deprecated
    private UserDTO convertToDto(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setLastName(user.getLastName());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setMiddleName(user.getMiddleName());
        userDTO.setBirthDate(user.getBirthDate());
        userDTO.setEmail(user.getEmail());
        userDTO.setPhone(user.getPhone());
        userDTO.setPhoto(user.getPhoto());
        return userDTO;
    }

    @Deprecated
    private User convertToEntity(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setLastName(userDTO.getLastName());
        user.setFirstName(userDTO.getFirstName());
        user.setMiddleName(userDTO.getMiddleName());
        user.setBirthDate(userDTO.getBirthDate());
        user.setEmail(userDTO.getEmail());
        user.setPhone(userDTO.getPhone());
        user.setPhoto(userDTO.getPhoto());
        return user;
    }
}
