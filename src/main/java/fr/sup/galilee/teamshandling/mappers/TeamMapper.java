package fr.sup.galilee.teamshandling.mappers;

import fr.sup.galilee.teamshandling.dtos.TeamDTO;
import fr.sup.galilee.teamshandling.entities.Team;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class TeamMapper {

    public TeamDTO toDto(Team team) {
        return TeamDTO.builder()
                .id(team.getId())
                .name(team.getName())
                .acronym(team.getAcronym())
                .build();
    }

    public Team toEntity(TeamDTO teamDTO) {
        Team team = new Team();
        team.setName(teamDTO.getName());
        team.setAcronym(teamDTO.getAcronym());
        return team;
    }
}
