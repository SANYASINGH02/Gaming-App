package com.example.aagApp.controller;

import com.example.aagApp.entity.Tournament;
import com.example.aagApp.model.PrizeDto;
import com.example.aagApp.model.TeamDto;
import com.example.aagApp.model.TournamentDto;
import com.example.aagApp.services.TournamentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tournament")
public class TournamentController {
    @Autowired
    private TournamentService tournamentService;

    @GetMapping("/getAll")
    public ResponseEntity<List<Tournament>> getAllTournaments() {
        System.out.println("Controller getTournaments");
        return tournamentService.getAllTournaments();
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createTournament(@RequestBody TournamentDto tournamentDto) {
               return tournamentService.createTournament(tournamentDto);
    }

    @GetMapping("/get/{tournamentId}")
    public ResponseEntity<Object> getTournamentById(@PathVariable Long tournamentId) {
        return tournamentService.getTournamentById(tournamentId);

    }

    @DeleteMapping("/delete/{tournamentId}")
    public ResponseEntity<Object> deleteTournament(@PathVariable Long tournamentId) {

       return tournamentService.deleteTournament(tournamentId);
    }

    @PutMapping("/endTournament/{tournamentId}")
    public ResponseEntity<?> endTournament(Long tournamentId) {
        return tournamentService.endTournament(tournamentId);
    }

    @GetMapping("/getTeams/{tournamentId}")
    public ResponseEntity<Object> getAllTeams(@PathVariable Long tournamentId) {
       return tournamentService.getAllTeams(tournamentId);
    }

    @PostMapping("/addTeam")
    public ResponseEntity<Object> addTeamToTournament(@RequestParam(value = "tournamentId") Long tournamentId,
            @RequestBody TeamDto teamDto) {
        System.out.println("controller add team to tour");
       return tournamentService.addTeamToTournament(tournamentId,teamDto);
    }

    @DeleteMapping("/removeTeam")
    public ResponseEntity<Object> removeTeamFromTournament(@RequestParam(value = "tournamentId") Long tournamentId,
                                                           @RequestParam(value = "teamId") Long teamId) {
        return tournamentService.removeTeamFromTournament(tournamentId,teamId);
    }

    @GetMapping("/getOngoingTournaments")
    public ResponseEntity<?> getOngoingTournaments() {
        return tournamentService.getOngoingTournaments();
    }

    @GetMapping("/getUpcomingTournaments")
    public ResponseEntity<?> getUpcomingTournaments() {
        return tournamentService.getUpcomingTournaments();
    }


    @PostMapping("/distributePrize")
    public ResponseEntity<?> distributePrize(@Valid @RequestBody PrizeDto prize) {
       return tournamentService.distributePrize(prize);
        }

        
}
