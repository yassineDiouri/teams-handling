package fr.sup.galilee.teamshandling.controllers;

import fr.sup.galilee.teamshandling.IdsExistsValidator;
import fr.sup.galilee.teamshandling.dtos.PlayerDTO;
import fr.sup.galilee.teamshandling.dtos.TransferPlayer;
import fr.sup.galilee.teamshandling.services.TransferService;
import lombok.AllArgsConstructor;
import org.springframework.validation.DataBinder;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("transfer")
public class TransferController {

    private final TransferService transferService;
    private final IdsExistsValidator idsExistsValidator;

    @InitBinder
    public void initBinder(DataBinder dataBinder) {
        if (dataBinder.getTarget() != null && idsExistsValidator.supports(dataBinder.getTarget().getClass())) {
            dataBinder.addValidators(idsExistsValidator);
        }
    }


    @GetMapping
    public List<PlayerDTO> transfer(@RequestParam(required = false) Boolean free) {
        if (free == null || free) {
            return transferService.getFreePlayers();
        }
        return transferService.getNotFreePlayers();
    }

    @PostMapping
    public void transferPlayer(@RequestBody @Validated TransferPlayer transferPlayer, Errors errors) {
        transferService.transferPlayer(transferPlayer);
    }
}
