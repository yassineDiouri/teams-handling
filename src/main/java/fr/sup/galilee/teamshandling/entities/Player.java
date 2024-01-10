package fr.sup.galilee.teamshandling.entities;

import fr.sup.galilee.teamshandling.enums.PlayerPosition;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstname;
    private String lastname;
    private Date birthdate;
    @Enumerated(EnumType.STRING)
    private PlayerPosition position;
    @ManyToOne
    private Team team;
}
