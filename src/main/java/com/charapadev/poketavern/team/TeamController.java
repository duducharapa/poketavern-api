package com.charapadev.poketavern.team;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/teams")
@RestController
public class TeamController {
    private final TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping
    public List<Team> list() {
        return teamService.list();
    }
    
    @PostMapping
    public Team create(@RequestBody CreateTeamDTO creationDTO) {
        return teamService.create(creationDTO);
    }
}
