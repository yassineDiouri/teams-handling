package fr.sup.galilee.teamshandling.services;

import fr.sup.galilee.teamshandling.dtos.TeamDTO;
import fr.sup.galilee.teamshandling.mappers.TeamMapper;
import fr.sup.galilee.teamshandling.repositories.TeamRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TeamService {

    private final TeamRepository teamRepository;
    private final TeamMapper teamMapper;

    @Autowired
    public TeamService(TeamRepository teamRepository, TeamMapper teamMapper) {
        this.teamRepository = teamRepository;
        this.teamMapper = teamMapper;
    }

    @Transactional(readOnly = true)
    public List<TeamDTO> findAll() {
        return teamRepository.findAll()
                .stream()
                .map(teamMapper::toDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public TeamDTO findByAcronym(String acronym) {
        return teamRepository.findByAcronym(acronym).map(teamMapper::toDto).orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    public void insert(TeamDTO teamDTO) {
        teamRepository.save(teamMapper.toEntity(teamDTO));
    }

    @Transactional
    public void deleteById(String id) {
        teamRepository.deleteById(id);
    }
}
