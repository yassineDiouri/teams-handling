package fr.sup.galilee.teamshandling.controllers;

import fr.sup.galilee.teamshandling.dtos.TeamDTO;
import fr.sup.galilee.teamshandling.services.TeamService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("teams")
@AllArgsConstructor
public class TeamController {

    private final TeamService service;

    @GetMapping
    public List<TeamDTO> getAll() {
        return service.findAll();
    }

    @GetMapping(path = "/{acronym}", produces = MediaType.APPLICATION_JSON_VALUE)
    public TeamDTO getByAcronym(@PathVariable String acronym) {
        return service.findByAcronym(acronym);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void add(@RequestBody TeamDTO teamDTO) {
        service.insert(teamDTO);
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable String id) {
        service.deleteById(id);
    }
}
