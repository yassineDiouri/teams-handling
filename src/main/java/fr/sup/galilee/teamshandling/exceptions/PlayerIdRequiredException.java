package fr.sup.galilee.teamshandling.exceptions;

import fr.sup.galilee.teamshandling.dtos.PlayerDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PlayerIdRequiredException extends RuntimeException {
    public PlayerDTO playerDTO;
}
