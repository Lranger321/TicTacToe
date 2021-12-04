package main.service.bot;

import lombok.AllArgsConstructor;
import main.dao.entity.BotDifficulty;
import main.dto.GameTurnResponseDTO;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@AllArgsConstructor
public class EasyBot implements Bot {

    @Override
    public GameTurnResponseDTO makeTurn(String[][] grid, String mark) throws Exception {
        int[][] freeSpace = findFreeSpaces(grid);
        int randomInt = new Random().nextInt();
        return new GameTurnResponseDTO(false, null, GameTurnResponseDTO.BotTurnDto.builder()
                .isWin(GameProcessUtil.makeTurn(grid, freeSpace[randomInt][0], freeSpace[randomInt][1], mark))
                .column(freeSpace[randomInt][0])
                .row(freeSpace[randomInt][1])
                .build());
    }

    @Override
    public BotDifficulty botDifficulty() {
        return BotDifficulty.EASY;
    }

    public int[][] findFreeSpaces(String[][] grid) {
        int[][] result = new int[grid.length * grid.length][2];
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                if (grid[i][j].isEmpty()) {
                    result[count][0] = i;
                    result[count][1] = j;
                    count++;
                }
            }
        }
        return result;
    }
}
