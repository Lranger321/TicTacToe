package main.controller;

import lombok.AllArgsConstructor;
import main.dto.GameResponseDTO;
import main.dto.GameTurnResponseDTO;
import main.dto.TurnDTO;
import main.service.GameService;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/games")
public class GameController {

    private final GameService gameService;

    @PostMapping("/makeTurn")
    public GameTurnResponseDTO makeTurn(@RequestBody TurnDTO turnDTO) {
        return gameService.makeTurn(turnDTO);
    }

    @PostMapping("/")
    public GameResponseDTO createGame() throws Exception {
        return gameService.createGame();
    }

}
