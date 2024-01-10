package fr.sup.galilee.teamshandling.mappers;

import fr.sup.galilee.teamshandling.dtos.PlayerDTO;
import fr.sup.galilee.teamshandling.dtos.TeamDTO;
import fr.sup.galilee.teamshandling.entities.Player;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class PlayerMapper {

    public PlayerDTO toDto(Player player) {
        PlayerDTO.PlayerDTOBuilder playerDTOBuilder = PlayerDTO.builder()
                .id(player.getId())
                .firstname(player.getFirstname())
                .lastname(player.getLastname())
                .position(player.getPosition())
                .birthdate(player.getBirthdate());
        if (player.getTeam() != null) {
            playerDTOBuilder.team(TeamDTO.builder()
                    .id(player.getTeam().getId())
                    .name(player.getTeam().getName())
                    .acronym(player.getTeam().getAcronym())
                    .build());
        }
        return playerDTOBuilder.build();
    }

    public Player toEntity(PlayerDTO playerDTO) {
        Player player = new Player();
        player.setId(playerDTO.getId());
        player.setFirstname(playerDTO.getFirstname());
        player.setLastname(playerDTO.getLastname());
        player.setPosition(playerDTO.getPosition());
        player.setBirthdate(playerDTO.getBirthdate());
        return player;
    }
}
