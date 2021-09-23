package main.service.impl;

import lombok.RequiredArgsConstructor;
import main.dao.entity.Game;
import main.dao.entity.GameResult;
import main.dao.entity.User;
import main.dao.repository.GameRepository;
import main.dao.repository.UserRepository;
import main.dto.GameResponseDTO;
import main.dto.GameTurnResponseDTO;
import main.dto.TurnDTO;
import main.exception.GameNotFoundException;
import main.service.GameService;
import main.service.bot.Bot;
import main.service.bot.BotConfiguration;
import main.service.bot.GameProcessUtil;
import main.service.util.TimeFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Optional;
import java.util.Random;

import static main.service.bot.GameProcessUtil.inverseMark;

@Service
@RequiredArgsConstructor
public class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;
    private final UserRepository userRepository;
    private final Bot bot;
    private User userBot;
    private BotConfiguration botConfiguration;
    private final PasswordEncoder encoder;
    private Random random = new Random();

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

    @Override
    public GameResponseDTO createGame(String login) throws GameNotFoundException {
        User user = userRepository.findByLogin(login).orElseThrow(()-> new GameNotFoundException(login));
        boolean userCrossIsBot = random.nextInt() % 2 == 1;
        Game game = gameRepository.save(Game.builder()
                .createdAt(TimeFactory.now())
                .result(GameResult.IN_PROGRESS)
                .userCross(userCrossIsBot ? user : userBot)
                .userCircle(!userCrossIsBot ? user : userBot)
                .build());
        return new GameResponseDTO(game.getId(), game.getCreatedAt());
    }

    @Override
    public GameTurnResponseDTO makeTurn(String login, TurnDTO turnDTO) throws GameNotFoundException {
        Game game = gameRepository.findById(turnDTO.getGameId()).orElseThrow(()-> new GameNotFoundException(turnDTO.getGameId()));
        String[][] grid = GameProcessUtil.createGrid(game.getHistories(), game.getUserCross().getId());
        try {
            boolean isWin = (game.getHistories().size() > 5) ?
                    GameProcessUtil.makeTurn(grid, turnDTO.getColumn(), turnDTO.getRow(), turnDTO.getMark()) : false;
            if (isWin) {
                return new GameTurnResponseDTO(true, null, null);
            }
            return new GameTurnResponseDTO(false, null, bot.makeTurn(grid, inverseMark(turnDTO.getMark())));
        } catch (Exception e) {
            return new GameTurnResponseDTO(false, e.getMessage(), null);
        }
    }

}
