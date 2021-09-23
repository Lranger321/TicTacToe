package main.service;

import main.dto.LoginDTO;
import main.dto.UserDTO;

public interface UserService {

    boolean login(LoginDTO dto);

    boolean register(UserDTO dto);
}
