package fr.sup.galilee.teamshandling.dtos;

import fr.sup.galilee.teamshandling.enums.PlayerPosition;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
public class PlayerDTO {
    @NotNull(groups = PlayerUpdate.class)
    private Long id;
    @NotBlank(groups = {PlayerCreation.class, PlayerUpdate.class})
    private String firstname;
    @NotBlank(groups = {PlayerCreation.class, PlayerUpdate.class})
    private String lastname;
    @NotNull(groups = {PlayerCreation.class, PlayerUpdate.class})
    private Date birthdate;
    @NotNull(groups = {PlayerCreation.class, PlayerUpdate.class})
    private PlayerPosition position;
    private TeamDTO team;

    public interface PlayerCreation{}
    public interface PlayerUpdate{}
}
