package com.example.demo.utils;

import com.example.demo.dtos.UserDTO;
import com.example.demo.models.User;
import org.mapstruct.*;

import java.util.List;

/**
 * Маппер ответственный за преобразование сущностей
 */
@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface UserMapper {

    UserDTO toUserDTO(User user);

    List<UserDTO> toUserDTOs(List<User> users);

    User toUser(UserDTO userDTO);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateUserFromDto(UserDTO userDTO, @MappingTarget User userEntity);
}