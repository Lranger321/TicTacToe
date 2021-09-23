package main.controller;

import lombok.AllArgsConstructor;
import main.dto.LoginDTO;
import main.dto.UserDTO;
import main.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController("/api/users")
public class LoginController {

    private final UserService userService;

    @PostMapping("/register")
    public boolean register(@RequestBody UserDTO dto){
        return userService.register(dto);
    }

    @PostMapping("/login")
    public boolean login(@RequestBody LoginDTO dto){
        return userService.login(dto);
    }
}
