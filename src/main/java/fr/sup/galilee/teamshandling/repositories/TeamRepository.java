package fr.sup.galilee.teamshandling.repositories;

import fr.sup.galilee.teamshandling.entities.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeamRepository extends JpaRepository<Team, String> {

    Optional<Team> findByAcronym(String acronym);
}
