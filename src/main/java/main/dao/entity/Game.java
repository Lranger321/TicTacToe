package main.dao.entity;

import lombok.*;
import main.dao.entity.impl.AbstractEntity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
@Table(name = "game")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "result", nullable = false)
    private GameResult result;

    @Column(name = "created_at", nullable = false)
    private LocalDate createdAt;

}
