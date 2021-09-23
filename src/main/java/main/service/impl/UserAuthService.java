package main.service.impl;

import lombok.RequiredArgsConstructor;
import main.dao.entity.User;
import main.dao.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserAuthService implements UserDetailsService {

    private final UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String login) {
        Optional<User> optionalUser = repository.findByLogin(login);
        if (optionalUser.isPresent() ) {
            User user = optionalUser.get();
            return new org.springframework.security.core.userdetails.User(user.getLogin(),
                    user.getPassword(), Collections.singletonList(new SimpleGrantedAuthority("USER")));
        }
        throw new UsernameNotFoundException(String.format("User with login %s not found", login));
    }

}
