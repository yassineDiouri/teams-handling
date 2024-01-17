package fr.sup.galilee.teamshandling.controllers;

import fr.sup.galilee.teamshandling.dtos.PlayerDTO;
import fr.sup.galilee.teamshandling.exceptions.PlayerDataNotValidException;
import fr.sup.galilee.teamshandling.exceptions.PlayerIdRequiredException;
import fr.sup.galilee.teamshandling.services.PlayerService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("players")
@AllArgsConstructor
public class PlayerController {

    private final PlayerService service;

    @GetMapping
    public List<PlayerDTO> getAll() {
        return service.findAll();
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public PlayerDTO getById(@PathVariable("id") Long acronym) {
        return service.findById(acronym);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void add(@RequestBody @Validated(PlayerDTO.PlayerCreation.class) PlayerDTO playerDTO, Errors errors) {
        if (errors.hasErrors()) {
            throw new PlayerDataNotValidException();
        }
        service.insert(playerDTO);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody @Validated(PlayerDTO.PlayerUpdate.class) PlayerDTO playerDTO, Errors errors) {
        if (errors.hasErrors()) {
            if (errors.getFieldError("id") != null){
                throw new PlayerIdRequiredException(playerDTO);
            }
            throw new PlayerDataNotValidException();
        }
        service.update(playerDTO);
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteById(id);
    }
}
