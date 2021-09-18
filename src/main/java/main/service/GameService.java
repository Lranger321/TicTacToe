package main.service;

import main.dto.GameResponseDTO;
import main.dto.GameTurnResponseDTO;
import main.dto.TurnDTO;

public interface GameService {

    GameResponseDTO createGame(Long id);

    GameTurnResponseDTO makeTurn(TurnDTO turnDTO);
}
