package dao.entity;

import dao.entity.impl.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "game")
public class Game extends AbstractEntity {

    @Column(name = "result", nullable = false)
    private String result;

    @Column(name = "created_at", nullable = false)
    private Date createdAt;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_cross")
    private User userCross;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_circle")
    private User userCircle;

}
