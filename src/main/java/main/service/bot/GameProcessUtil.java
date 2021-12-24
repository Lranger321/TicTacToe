package main.service.bot;

import main.dao.entity.History;

import java.util.List;

public class GameProcessUtil {

    private static final String CROSS = "cross";
    private static final String CIRCLE = "circle";

    public static String[][] createGrid(List<History> historyList, Long userCrossId) {
        String[][] grid = new String[3][3];
//        for (History history : historyList) {
//            grid[history.getColumn()][history.getRow()] = (history.getUser().getId().equals(userCrossId)) ?
//                    CROSS : CIRCLE;
//        }
        return grid;
    }

    public static boolean makeTurn(String[][] grid, int column, int row, String mark) throws Exception {
        if (column > 3 || row > 3) {
            throw new Exception("Column or row out of range 3");
        }
        if (!grid[column][row].isEmpty()) {
            throw new Exception("Is already used");
        }
        grid[column][row] = mark;
        return isWin(grid, mark);
    }

    private static boolean isWin(String[][] grid, String mark) {
        //row
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                if (!grid[i][j].equals(mark)) {
                    break;
                }
                if (j == grid.length - 1) {
                    return true;
                }
            }
        }
        // column
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                if (!grid[j][i].equals(mark)) {
                    break;
                }
                if (j == grid.length - 1) {
                    return true;
                }
            }
        }

        // diagonals
        for (int i = 0; i < grid.length; i++) {
            if (!grid[i][i].equals(mark)) {
                break;
            }
            if (i == grid.length - 1) {
                return true;
            }
        }

        for (int i = 0; i < grid.length; i++) {
            if (!grid[grid.length - i - 1][grid.length - i - 1].equals(mark)) {
                break;
            }
            if (i == grid.length - 1) {
                return true;
            }
        }

        return false;
    }

    public static String inverseMark(String mark) {
        return mark.equals(CROSS) ? CIRCLE : CROSS;
    }
}
