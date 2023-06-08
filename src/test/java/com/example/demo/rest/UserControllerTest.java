package com.example.demo.rest;

import com.example.demo.dtos.UserDTO;
import com.example.demo.models.User;
import com.example.demo.services.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ComponentScan(basePackages = {"com.example"})
@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

//    @Mock
//    private UserService userService;
//
//    @Autowired
//    private MockMvc mockMvc;

    @Test
    void findById_userExists_returnsUser() {
        // Given
        Long id = 1L;
        User user = new User();
        user.setId(id);

        // When
        UserDTO userDTO = userController.findById(id);

        // Then
        assertEquals(id, userDTO.getId());
    }

    @Test
    void findById_userDoesNotExist_throwsNotFoundException() {
        // Given
        Long id = 1L;

        // When
        assertThrows(ChangeSetPersister.NotFoundException.class, () -> userController.findById(id));
    }

    @Test
    void findAll_usersExist_returnsListOfUsers() {
        // Given
        List<User> users = Arrays.asList(new User(), new User());

        // When
        List<UserDTO> userDTOs = userController.findAll();

        // Then
        assertEquals(2, userDTOs.size());
    }

    @Test
    void findAll_noUsersExist_returnsEmptyList() {
        // Given
        List<User> users = Collections.emptyList();

        // When
        List<UserDTO> userDTOs = userController.findAll();

        // Then
        assertTrue(userDTOs.isEmpty());
    }

    @Test
    void create_userIsValid_returnsUser() {
        // Given
        UserDTO userDTO = new UserDTO();
        userDTO.setFirstName("John");
        userDTO.setLastName("Doe");
        userDTO.setEmail("johndoe@example.com");

        // When
        UserDTO createdUserDTO = userController.create(userDTO);

        // Then
        assertEquals(userDTO.getFirstName(), createdUserDTO.getFirstName());
        assertEquals(userDTO.getLastName(), createdUserDTO.getLastName());
        assertEquals(userDTO.getEmail(), createdUserDTO.getEmail());
    }

    @Test
    void create_userIsInvalid_throwsBadRequestException() {
        // Given
        UserDTO userDTO = new UserDTO();
        userDTO.setFirstName(null);

        // When
        assertThrows(HttpClientErrorException.BadRequest.class, () -> userController.create(userDTO));
    }

    @Test
    void update_userIsValid_updatesUser() {
        // Given
        User user = new User();
        user.setId(1L);
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setEmail("johndoe@example.com");

        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setFirstName("Jane");

        // When
        userController.update(userDTO);

        // Then
        assertEquals(userDTO.getFirstName(), user.getFirstName());
    }

    @Test
    void update_userIsInvalid_throwsBadRequestException() {
        // Given
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1L);
        userDTO.setFirstName(null);

        // When
        assertThrows(HttpClientErrorException.BadRequest.class, () -> userController.update(userDTO));
    }


    @Test
    void deleteById_userExists_deletesUser() {
        // Given
        User user = new User();
        user.setId(1L);
        userService.save(user);

        // When
        userController.deleteById(user.getId());

        // Then
        assertEquals(userService.findById(user.getId()), is(nullValue()));
    }

    private User buildUser() {
        User user = new User();
        user.setId(1L);
        user.setFirstName("John");
        user.setLastName("Doe");

        return user;
    }

    private UserDTO buildUserDto() {
        UserDTO userDto = new UserDTO();
        userDto.setId(1L);
        userDto.setFirstName("John");
        userDto.setLastName("Doe");

        return userDto;
    }
}