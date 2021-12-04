package main.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum BotDifficulty {
    EASY("easy"),
    MEDIUM("medium"),
    HARD("hard");

    private String difficulty;


    public static BotDifficulty getByDifficulty(String value){
        for(BotDifficulty difficulty : BotDifficulty.values()){
            if(difficulty.getDifficulty().equals(value)){
                return difficulty;
            }
        }
        return null;
    }
}
