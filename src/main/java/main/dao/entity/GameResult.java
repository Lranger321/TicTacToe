package main.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum GameResult {
    CROSS_WIN("crossWin"),
    CIRCLE_WIN("circleWin"),
    TIE("tie"),
    IN_PROGRESS("inProgress");

    private String result;
}
