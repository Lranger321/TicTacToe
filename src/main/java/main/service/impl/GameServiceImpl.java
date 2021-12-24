package main.service.impl;

import lombok.RequiredArgsConstructor;
import main.dao.entity.Game;
import main.dao.entity.GameResult;
import main.dao.entity.History;
import main.dao.repository.GameRepository;
import main.dao.repository.HistoryRepository;
import main.dao.repository.UserRepository;
import main.dto.GameResponseDTO;
import main.dto.GameTurnResponseDTO;
import main.dto.TurnDTO;
import main.exception.GameNotFoundException;
import main.service.GameService;
import main.service.bot.EasyBot;
import main.service.util.TimeFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;
    private final HistoryRepository repository;
    private final UserRepository userRepository;
    private final UserServiceImpl userService;

    @Override
    public GameResponseDTO createGame() throws Exception {
        Game game = gameRepository.save(Game.builder()
                .createdAt(TimeFactory.now())
                .result(GameResult.IN_PROGRESS)
                .build());
        return new GameResponseDTO(game.getId(), game.getCreatedAt());
    }

    @Override
    public GameTurnResponseDTO makeTurn(TurnDTO turnDTO) {
        Game game = gameRepository.findById(turnDTO.getGameId()).orElseThrow(() -> new GameNotFoundException(turnDTO.getGameId()));
        History history = History.builder()
                .gameId(game.getId())
                .column(String.valueOf(turnDTO.getColumn()))
                .row(String.valueOf(turnDTO.getRow()))
                .figure(turnDTO.getMark())
                .build();
        repository.save(history);
        gameRepository.save(game);
        return null;
    }

}
