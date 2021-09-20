package main.service;

import main.dto.GameResponseDTO;
import main.dto.GameTurnResponseDTO;
import main.dto.TurnDTO;
import main.exception.GameNotFoundException;

public interface GameService {

    GameResponseDTO createGame(Long id) throws GameNotFoundException;

    GameTurnResponseDTO makeTurn(TurnDTO turnDTO);
}
