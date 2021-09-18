package main.dto;

import lombok.AllArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
public class GameResponseDTO {

    private Long id;
    private LocalDate createdAt;
}
