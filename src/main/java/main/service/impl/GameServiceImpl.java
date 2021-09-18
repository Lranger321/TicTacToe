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
import main.service.GameService;
import main.service.bot.Bot;
import main.service.bot.BotConfiguration;
import main.service.bot.GameProcessUtil;
import main.service.util.TimeFactory;
import org.springframework.stereotype.Service;

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
    private Random random = new Random();

    public void createBot() {
        Optional<User> bot = userRepository.findById(0L);
        if (bot.isEmpty()) {
            userBot = User.builder()
                    .id(0L)
                    .login(botConfiguration.getLogin())
                    .password(botConfiguration.getPassword())
                    .build();
        } else {
            userBot = bot.get();
        }
    }

    @Override
    public GameResponseDTO createGame(Long id) {
        User user = userRepository.findById(id).get();
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
    public GameTurnResponseDTO makeTurn(TurnDTO turnDTO) {
        Game game = gameRepository.findById(turnDTO.getGameId()).get();
        String[][] grid = GameProcessUtil.createGrid(game.getHistories(), game.getUserCross().getId());
        try {
            boolean isWin = GameProcessUtil.makeTurn(grid, turnDTO.getColumn(), turnDTO.getRow(), turnDTO.getMark());
            if (isWin) {
                return new GameTurnResponseDTO(true, null, null);
            }
            return new GameTurnResponseDTO(false, null, bot.makeTurn(grid, inverseMark(turnDTO.getMark())));
        } catch (Exception e) {
            return new GameTurnResponseDTO(false, e.getMessage(), null);
        }
    }

}
