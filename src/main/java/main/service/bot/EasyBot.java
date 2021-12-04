package main.service.bot;

import lombok.AllArgsConstructor;
import lombok.Getter;
import main.dao.entity.User;
import main.dao.repository.UserRepository;
import main.dto.GameTurnResponseDTO;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EasyBot implements Bot {

    @Getter
    private User userBot;
    private BotConfiguration botConfiguration;
    private final PasswordEncoder encoder;
    private final UserRepository userRepository;

    @PostConstruct
    public void init() {
        Optional<User> bot = userRepository.findByLogin("bot");
        if (bot.isEmpty()) {
            userBot = User.builder()
                    .login("bot")
                    .name("bot")
                    .password(encoder.encode("bot"))
                    .build();
            userRepository.save(userBot);
        } else {
            userBot = bot.get();
        }
    }

    public GameTurnResponseDTO makeTurn(String[][] grid, String mark){

        return null;
    }

    @Override
    public GameTurnResponseDTO makeTurn() {
        return null;
    }

    @Override
    public BotDifficulty getDifficulty() {
        return null;
    }
}
