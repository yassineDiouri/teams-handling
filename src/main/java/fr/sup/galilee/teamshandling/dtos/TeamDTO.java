package fr.sup.galilee.teamshandling.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class TeamDTO {
    private String id;
    private String name;
    private String acronym;
}
