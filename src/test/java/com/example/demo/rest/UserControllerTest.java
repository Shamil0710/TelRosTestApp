package com.example.demo.rest;

import com.example.demo.dtos.UserDTO;
import com.example.demo.models.User;
import com.example.demo.services.UserServiceImp;
import com.example.demo.utils.UserMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;
    @Mock
    private UserServiceImp userService;
    @Mock
    private UserMapper userMapper;
    @InjectMocks
    private UserController userController;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
        objectMapper = new ObjectMapper();
//        userService = Mockito.mock(UserService.class);
    }

    @Test
    public void whenGetAllUsers_thenAllUsersReturned() throws Exception {
        User user1 = new User();
        user1.setId(1L);
        user1.setLastName("Doe");
        user1.setFirstName("John");
        List<User> users = Collections.singletonList(user1);

        UserDTO userDTO = new UserDTO();
        userDTO.setId(1L);
        userDTO.setLastName("Doe");
        userDTO.setFirstName("John");
        List<UserDTO> userDTOS = Collections.singletonList(userDTO);

        Mockito.when(userMapper.toUserDTOs(any())).thenReturn(userDTOS);

        mockMvc.perform(get("/api/users")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(objectMapper.writeValueAsString(users)))
                .andExpect(status().isOk())
                .andReturn();


        verify(userService).findAll();
    }

    @Test
    public void whenGetOneUser_thenOneUserReturned() throws Exception {
        UserDTO userDto = new UserDTO();
        userDto.setId(1L);
        userDto.setLastName("Doe");
        userDto.setFirstName("John");

        User user = new User();
        user.setId(1L);
        user.setFirstName("John");
        user.setLastName("Doe");

        Mockito.when(userService.findById(1L)).thenReturn(user);
        Mockito.when(userMapper.toUserDTO(any())).thenReturn(userDto);

        mockMvc.perform(get("/api/users/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk());

        verify(userService).findById(1L);
    }

    @Test
    public void whenSaveUser_thenUserSaved() throws Exception {
        UserDTO userDto = new UserDTO();
        userDto.setId(1L);
        userDto.setLastName("Doe");
        userDto.setFirstName("John");

        Mockito.when(userMapper.toUserDTO(any())).thenReturn(userDto);

        mockMvc.perform(post("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userDto)))
                .andExpect(status().isOk());

        verify(userMapper).toUser(userDto);
    }

    @Test
    public void whenEditUser_thenUserFound() throws Exception {
        UserDTO userDto = new UserDTO();
        userDto.setId(1L);
        userDto.setLastName("Doe");
        userDto.setFirstName("John");

        mockMvc.perform(put("/api/users/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userDto)))
                .andExpect(status().isOk());

        verify(userService).updateUserFromDto(userDto);
    }

    @Test
    public void whenDeleteUser_thenUserDeleted() throws Exception {
        Mockito.doNothing().when(userService).deleteById(1L);

        mockMvc.perform(delete("/api/users/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(userService).deleteById(1L);
    }
}