package fr.sup.galilee.teamshandling.controllers;

import fr.sup.galilee.teamshandling.dtos.PlayerDTO;
import fr.sup.galilee.teamshandling.dtos.TransferPlayer;
import fr.sup.galilee.teamshandling.services.TransferService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("transfer")
public class TransferController {

    private final TransferService transferService;

    @GetMapping
    public List<PlayerDTO> transfer(@RequestParam(required = false) Boolean free) {
        if (free == null || free) {
            return transferService.getFreePlayers();
        }
        return transferService.getNotFreePlayers();
    }

    @PostMapping
    public void transferPlayer(@RequestBody TransferPlayer transferPlayer) {
        transferService.transferPlayer(transferPlayer);
    }
}
