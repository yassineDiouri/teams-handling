package fr.sup.galilee.teamshandling.dtos;

import fr.sup.galilee.teamshandling.enums.PlayerPosition;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
public class PlayerDTO {
    private Long id;
    private String firstname;
    private String lastname;
    private Date birthdate;
    private PlayerPosition position;
    private TeamDTO team;
}
