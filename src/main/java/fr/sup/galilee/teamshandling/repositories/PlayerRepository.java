package fr.sup.galilee.teamshandling.repositories;

import fr.sup.galilee.teamshandling.entities.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {
    List<Player> findAllByTeamIdNull();

    List<Player> findAllByTeamIdNotNull();
}
