package com.example.demo.utils;

import com.example.demo.dtos.UserDTO;
import com.example.demo.models.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.*;

class UserMapperTest {

    @Test
    public void testToUserDTO() {
        User user = new User();
        user.setId(1L);
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setEmail("johndoe@example.com");

        UserMapper userMapper = Mappers.getMapper(UserMapper.class);

        UserDTO userDTO = userMapper.toUserDTO(user);

        Assertions.assertEquals(1L, userDTO.getId());
        Assertions.assertEquals("John", userDTO.getFirstName());
        Assertions.assertEquals("Doe", userDTO.getLastName());
        Assertions.assertEquals("johndoe@example.com", userDTO.getEmail());
    }

    @Test
    public void testToUser() {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1L);
        userDTO.setFirstName("John");
        userDTO.setLastName("Doe");
        userDTO.setEmail("johndoe@example.com");

        UserMapper userMapper = Mappers.getMapper(UserMapper.class);

        User user = userMapper.toUser(userDTO);

        Assertions.assertEquals(1L, user.getId());
        Assertions.assertEquals("John", user.getFirstName());
        Assertions.assertEquals("Doe", user.getLastName());
        Assertions.assertEquals("johndoe@example.com", user.getEmail());
    }

}