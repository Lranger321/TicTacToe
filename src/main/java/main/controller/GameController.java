package main.controller;

import lombok.AllArgsConstructor;
import main.dto.GameCreateDto;
import main.dto.GameResponseDTO;
import main.dto.GameTurnResponseDTO;
import main.dto.TurnDTO;
import main.exception.GameNotFoundException;
import main.service.GameService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@AllArgsConstructor
@RestController("/api/games")
public class GameController {

    private final GameService gameService;

    @PreAuthorize("hasAuthority('USER')")
    @PostMapping("/makeTurn")
    public GameTurnResponseDTO makeTurn(Principal principal, TurnDTO turnDTO) {
        return gameService.makeTurn(principal.getName(), turnDTO);
    }

    @PreAuthorize("hasAuthority('USER')")
    @PostMapping("/")
    public GameResponseDTO createGame(GameCreateDto dto, Principal principal) {
        return gameService.createGame(dto,principal.getName());
    }

}
