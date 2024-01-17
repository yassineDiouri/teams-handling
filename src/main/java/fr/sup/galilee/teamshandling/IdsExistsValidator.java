package fr.sup.galilee.teamshandling;

import fr.sup.galilee.teamshandling.dtos.TransferPlayer;
import fr.sup.galilee.teamshandling.repositories.PlayerRepository;
import fr.sup.galilee.teamshandling.repositories.TeamRepository;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class IdsExistsValidator implements Validator {

    private final PlayerRepository playerRepository;
    private final TeamRepository teamRepository;

    public IdsExistsValidator(PlayerRepository playerRepository, TeamRepository teamRepository) {
        this.playerRepository = playerRepository;
        this.teamRepository = teamRepository;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(TransferPlayer.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        TransferPlayer transferPlayer = (TransferPlayer) target;
        if (!playerRepository.existsById(transferPlayer.playerId())
                || !teamRepository.existsById(transferPlayer.teamId())) {
            errors.reject("Ids are not valid");
        }
    }
}
