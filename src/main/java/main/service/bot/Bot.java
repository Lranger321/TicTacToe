package main.service.bot;

import main.dao.entity.BotDifficulty;
import main.dto.GameTurnResponseDTO;

public interface Bot {

    GameTurnResponseDTO makeTurn(String[][] grid, String mark) throws Exception;

    BotDifficulty botDifficulty();

}
