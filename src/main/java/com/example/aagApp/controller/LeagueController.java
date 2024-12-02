package com.example.aagApp.controller;

import com.example.aagApp.entity.League;
import com.example.aagApp.entity.Team;
import com.example.aagApp.model.LeagueDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.example.aagApp.services.LeagueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/league")
public class LeagueController {

    @Autowired
    private LeagueService leagueService;


    // Create a new League
    @PostMapping("/create")
    public String createLeague(@RequestBody League League) {

        return leagueService.createLeague(League);
    }

    // Get all Leagues
    @GetMapping("/getAll")
    public List<LeagueDto> getAllLeagues() {
        return leagueService.getAllLeagues();
    }

    // Get League by ID
    @GetMapping("/get/{id}")
    public ResponseEntity<?> getLeagueById(@PathVariable Long id) {
        try {
            Optional<League> league = leagueService.getLeagueById(id);
            if (league.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("League not found with ID: " + id);
            }
            LeagueDto leagueDto = new LeagueDto(league.get().getId(), league.get().getName());
            return ResponseEntity.ok(leagueDto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An unexpected error occurred: " + e.getMessage());
        }
    }

    // Update League
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateLeague(@PathVariable Long id, @RequestBody League League) {
        return ResponseEntity.ok(leagueService.updateLeague(id, League));
    }

    // Delete League
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteLeague(@PathVariable Long id) {
        leagueService.deleteLeague(id);
        return ResponseEntity.ok("League Deleted");
    }

    // Add a team to a League
    @PostMapping("/addTeam")
    public String addTeamToLeague(@RequestParam(value="leagueId") Long LeagueId, @RequestBody Team team) {
        System.out.println("add team to league");
        return leagueService.addTeamToLeague(LeagueId, team);
    }

    // Delete a team from a League
    @DeleteMapping("/deleteTeam")
    public String deleteTeamFromLeague(@RequestParam(value="leagueId") Long leagueId, @RequestParam(value="teamId") Long teamId) {

        return leagueService.deleteTeamFromLeague(leagueId,teamId);
    }
}

