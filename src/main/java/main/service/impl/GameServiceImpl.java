package main.service.impl;

import lombok.RequiredArgsConstructor;
import main.dao.entity.*;
import main.dao.repository.GameRepository;
import main.dao.repository.UserRepository;
import main.dto.GameCreateDto;
import main.dto.GameResponseDTO;
import main.dto.GameTurnResponseDTO;
import main.dto.TurnDTO;
import main.exception.GameNotFoundException;
import main.exception.UserNotFoundException;
import main.service.GameService;
import main.service.bot.EasyBot;
import main.service.bot.GameProcessUtil;
import main.service.util.TimeFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

import static main.service.bot.GameProcessUtil.inverseMark;

@Service
@RequiredArgsConstructor
public class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;
    private final UserRepository userRepository;
    private final EasyBot bot;
    private Random random = new Random();

    @Override
    public GameResponseDTO createGame(GameCreateDto dto, String login) {
        User user = userRepository.findByLogin(login).orElseThrow(() -> new UserNotFoundException(login));
        boolean userCrossIsBot = random.nextInt() % 2 == 1;
        Game game = gameRepository.save(Game.builder()
                .createdAt(TimeFactory.now())
                .result(GameResult.IN_PROGRESS)
                .userCross(userCrossIsBot ? user : bot.getUserBot())
                .userCircle(!userCrossIsBot ? user : bot.getUserBot())
                .botDifficulty(BotDifficulty.getByDifficulty(dto.getBotDifficulty()))
                .build());
        return startGame(game);
    }

    @Override
    public GameTurnResponseDTO makeTurn(String login, TurnDTO turnDTO) {
        Game game = gameRepository.findById(turnDTO.getGameId()).orElseThrow(() -> new GameNotFoundException(turnDTO.getGameId()));
        User user = userRepository.findByLogin(login).orElseThrow(() -> new UserNotFoundException(login));
        List<History> historyList = game.getHistories();
        History history = History.builder()
                .game(game)
                .column(turnDTO.getColumn())
                .row(turnDTO.getRow())
                .figure(turnDTO.getMark())
                .user(user)
                .build();
        historyList.add(history);
        String[][] grid = GameProcessUtil.createGrid(historyList, game.getUserCross().getId());
        try {
            boolean isWin = (game.getHistories().size() > 5) ?
                    GameProcessUtil.makeTurn(grid, turnDTO.getColumn(), turnDTO.getRow(), turnDTO.getMark()) : false;
            GameTurnResponseDTO botTurn;
            if (isWin) {
                botTurn = new GameTurnResponseDTO(true, null, null);
            } else {
                botTurn = bot.makeTurn(grid, inverseMark(turnDTO.getMark()));
                historyList.add(History.builder()
                        .game(game)
                        .figure(inverseMark(turnDTO.getMark()))
                        .row(botTurn.getBotTurn().getRow())
                        .user(bot.getUserBot())
                        .column(botTurn.getBotTurn().getColumn())
                        .build());
            }
            game.setHistories(historyList);
            gameRepository.save(game);
            return botTurn;
        } catch (Exception e) {
            return new GameTurnResponseDTO(false, e.getMessage(), null);
        }
    }

    private GameResponseDTO startGame(Game game) {
        if (new Random().nextInt(2) == 0) {
            String[][] grid = GameProcessUtil.createGrid(game.getHistories(), game.getUserCross().getId());
            new GameResponseDTO(game.getId(), game.getCreatedAt(), bot.makeTurn(grid,
                    inverseMark(!game.getUserCross().equals(bot.getUserBot()) ? "cross" : "circle")));
        }
        return new GameResponseDTO(game.getId(), game.getCreatedAt(), null);
    }

}
