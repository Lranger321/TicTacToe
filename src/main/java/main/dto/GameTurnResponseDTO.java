package main.dto;

import lombok.*;

@AllArgsConstructor
@Data
public class GameTurnResponseDTO {

    private boolean isWin;
    private String error;
    private BotTurnDto botTurn;

    @Getter
    @AllArgsConstructor
    @Builder
    public static class BotTurnDto {

        private int column;
        private int row;
        private boolean isWin;
    }
}
