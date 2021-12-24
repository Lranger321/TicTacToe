package main.dao.entity;

import main.dao.entity.impl.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "history")
public class History {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "figure", nullable = false)
    private String figure;

    @Column(name = "game_row", nullable = false)
    private String row;

    @Column(name = "game_column", nullable = false)
    private String column;

    @Column(name = "game_id", nullable = false)
    private Long gameId;

}
