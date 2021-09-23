package main.service.mapper;

import main.dao.entity.User;
import main.dto.UserDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toUserEntity(UserDTO dto);
}
