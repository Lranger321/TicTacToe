package main.service;

import main.dto.GameCreateDto;
import main.dto.GameResponseDTO;
import main.dto.GameTurnResponseDTO;
import main.dto.TurnDTO;
import main.exception.GameNotFoundException;

public interface GameService {

    GameResponseDTO createGame() throws Exception;

    GameTurnResponseDTO makeTurn(TurnDTO turnDTO) throws GameNotFoundException;
}
