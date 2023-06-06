package com.example.demo.services;

import com.example.demo.UserRepository;
import com.example.demo.models.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void save() {

        User user = this.buildTestingUser();

        this.userService.save(user);

        Mockito.verify(this.userRepository).save(user);
    }

    @Test
    void findById() {
        User user = this.buildTestingUser();

        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        User returnedUser = this.userService.findById(1L);
        assertEquals(user.getId(), returnedUser.getId());

        Mockito.verify(this.userRepository).findById(1L);
    }

    @Test
    void findAll() {
        User user = this.buildTestingUser();

        Mockito.when(userRepository.findAll()).thenReturn(List.of(user));
        List<User> users = this.userService.findAll();

        assertEquals(1, users.size());
        Mockito.verify(this.userRepository).findAll();
    }

    @Test
    void deleteById() {
        this.userService.deleteById(1L);

        Mockito.verify(this.userRepository).deleteById(1L);
    }

    private User buildTestingUser() {
        User user = new User();
        user.setId(1L);
        user.setFirstName("John");
        user.setLastName("Doe");

        return user;
    }
}