package fr.sup.galilee.teamshandling.services;

import fr.sup.galilee.teamshandling.dtos.PlayerDTO;
import fr.sup.galilee.teamshandling.dtos.TransferPlayer;
import fr.sup.galilee.teamshandling.mappers.PlayerMapper;
import fr.sup.galilee.teamshandling.repositories.PlayerRepository;
import fr.sup.galilee.teamshandling.repositories.TeamRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TransferService {

    private final PlayerRepository playerRepository;
    private final TeamRepository teamRepository;
    private final PlayerMapper playerMapper;

    public List<PlayerDTO> getFreePlayers() {
        return playerRepository.findAllByTeamIdNull()
                .stream()
                .map(playerMapper::toDto)
                .toList();
    }

    public List<PlayerDTO> getNotFreePlayers() {
        return playerRepository.findAllByTeamIdNotNull()
                .stream()
                .map(playerMapper::toDto)
                .toList();
    }

    public void transferPlayer(TransferPlayer transferPlayer) {
        playerRepository.findById(transferPlayer.playerId())
                .ifPresent(
                        player -> {
                            teamRepository.findById(transferPlayer.teamId())
                                    .ifPresent(
                                            team -> {
                                                player.setTeam(team);
                                                playerRepository.save(player);
                                            }
                                    );
                        }
                );
    }
}
