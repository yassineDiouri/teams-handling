package fr.sup.galilee.teamshandling;

import fr.sup.galilee.teamshandling.entities.Player;
import fr.sup.galilee.teamshandling.entities.Team;
import fr.sup.galilee.teamshandling.enums.PlayerPosition;
import fr.sup.galilee.teamshandling.repositories.PlayerRepository;
import fr.sup.galilee.teamshandling.repositories.TeamRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Date;

@SpringBootApplication
public class TeamsHandlingApplication {

    public static void main(String[] args) {

        ConfigurableApplicationContext context = SpringApplication.run(TeamsHandlingApplication.class, args);

        TeamRepository teamRepository = context.getBean(TeamRepository.class);
        Team team = new Team();
        team.setName("Telecom 3");
        team.setAcronym("TL3");
        teamRepository.save(team);

        PlayerRepository playerRepository = (PlayerRepository) context.getBean("playerRepository");
        Player player = new Player();
        player.setFirstname("DIOURI");
        player.setLastname("Yassine");
        player.setPosition(PlayerPosition.MIDFIELD);
        player.setBirthdate(new Date());
        playerRepository.save(player);
    }

}
