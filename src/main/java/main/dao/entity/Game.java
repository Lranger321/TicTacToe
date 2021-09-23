package main.dao.entity;

import main.dao.entity.impl.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "game")
public class Game extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "result", nullable = false)
    private GameResult result;

    @Column(name = "created_at", nullable = false)
    private LocalDate createdAt;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_cross")
    private User userCross;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_circle")
    private User userCircle;

    @OneToMany(mappedBy = "game", orphanRemoval = true)
    private List<History> histories;

}
