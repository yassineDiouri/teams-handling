package fr.sup.galilee.teamshandling.services;

import fr.sup.galilee.teamshandling.dtos.PlayerDTO;
import fr.sup.galilee.teamshandling.mappers.PlayerMapper;
import fr.sup.galilee.teamshandling.repositories.PlayerRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class PlayerService {

    private final PlayerRepository playerRepository;
    private final PlayerMapper playerMapper;

    @Transactional(readOnly = true)
    public List<PlayerDTO> findAll() {
        return playerRepository.findAll()
                .stream()
                .map(playerMapper::toDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public PlayerDTO findById(Long id) {
        return playerRepository.findById(id).map(playerMapper::toDto).orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    public void insert(PlayerDTO playerDTO) {
        playerRepository.save(playerMapper.toEntity(playerDTO));
    }

    @Transactional
    public void update(PlayerDTO playerDTO) {
        playerRepository.findById(playerDTO.getId())
                .ifPresent(
                        player -> {
                            player.setFirstname(playerDTO.getFirstname());
                            player.setLastname(playerDTO.getLastname());
                            player.setBirthdate(playerDTO.getBirthdate());
                            player.setPosition(playerDTO.getPosition());
                            playerRepository.save(player);
                        }
                );
    }

    @Transactional
    public void deleteById(Long id) {
        playerRepository.deleteById(id);
    }
}
