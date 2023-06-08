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
    void givenUser_whenSave_thenUserIsSaved() {

        // Given
        User user = buildUser();

        // When
        userService.save(user);

        // Then
        Mockito.verify(userRepository).save(user);
    }

    @Test
    void givenId_whenFindById_thenUserIsFound() {

        // Given
        User user = buildUser();
        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        // When
        User returnedUser = userService.findById(1L);

        // Then
        assertEquals(user, returnedUser);

        Mockito.verify(userRepository).findById(1L);
    }

    @Test
    void givenNoUsers_whenFindAll_thenEmptyListIsReturned() {

        // Given
        Mockito.when(userRepository.findAll()).thenReturn(List.of());

        // When
        List<User> users = userService.findAll();

        // Then
        assertEquals(0, users.size());

        Mockito.verify(userRepository).findAll();
    }

    @Test
    void givenUserId_whenDeleteById_thenUserIsDeleted() {

        // Given
        User user = buildUser();

        // When
        userService.deleteById(1L);

        // Then
        Mockito.verify(userRepository).deleteById(1L);
    }

    private User buildUser() {
        User user = new User();
        user.setId(1L);
        user.setFirstName("John");
        user.setLastName("Doe");

        return user;
    }
}