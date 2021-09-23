package main.service.impl;

import lombok.RequiredArgsConstructor;
import main.dao.entity.User;
import main.dao.repository.UserRepository;
import main.dto.LoginDTO;
import main.dto.UserDTO;
import main.service.UserService;
import main.service.mapper.UserMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final DaoAuthenticationProvider authenticationManager;
    private final PasswordEncoder passwordEncoder;
   // private final UserMapper mapper;

    @Override
    public boolean login(LoginDTO dto) {
        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(dto.getLogin(), dto.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(auth);
        } catch (BadCredentialsException ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean register(UserDTO dto) {
        User user = User.builder()
                .password(passwordEncoder.encode(dto.getPassword()))
                .login(dto.getLogin())
                .name(dto.getName())
                .build();
        userRepository.save(user);
        return true;
    }
}
