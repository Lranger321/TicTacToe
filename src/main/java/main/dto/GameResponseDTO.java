package main.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Setter
public class GameResponseDTO {

    private Long id;
    private LocalDate createdAt;
    private GameTurnResponseDTO turn;
}
