package main.controller;

import lombok.AllArgsConstructor;
import main.dto.GameResponseDTO;
import main.dto.GameTurnResponseDTO;
import main.dto.TurnDTO;
import main.service.GameService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController("/api/games")
public class GameController {

    private final GameService gameService;

    @PostMapping("/makeTurn")
    public GameTurnResponseDTO makeTurn(TurnDTO turnDTO){
        return gameService.makeTurn(turnDTO);
    }

    @PostMapping("/")
    public GameResponseDTO createGame(Long id){
        return gameService.createGame(id);
    }

}
