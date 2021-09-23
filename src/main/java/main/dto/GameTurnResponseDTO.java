package main.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class GameTurnResponseDTO {

    private boolean isWin;
    private String error;
    private BotTurnDto botTurn;

    public static class BotTurnDto {

        private int column;
        private int row;
        private boolean isWin;
    }
}
