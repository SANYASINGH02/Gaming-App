package com.example.aagApp.controller;

import com.example.aagApp.entity.Team;
import com.example.aagApp.model.TeamDto;
import com.example.aagApp.services.LeagueService;
import com.example.aagApp.services.TeamService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/team")
public class TeamController {

    @Autowired
    private TeamService teamService;

    @Autowired
    private LeagueService leagueService;

    @GetMapping("/getAll")
    public List<Team> getAllTeams() {
        System.out.println("iam in");
        return teamService.getAllTeams();
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getTeamById(@PathVariable("id") Long id) {

            return teamService.getTeamById(id);
    }

    @PostMapping("/create")
    public Team createTeam(@RequestBody TeamDto teamDto) {

        return teamService.createTeam(teamDto);
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateTeam(@PathVariable Long id,@Valid @RequestBody Team teamDetails) {

        return teamService.updateTeam(id, teamDetails);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteTeam(@PathVariable Long id) {

        return teamService.deleteTeam(id);
    }
}
