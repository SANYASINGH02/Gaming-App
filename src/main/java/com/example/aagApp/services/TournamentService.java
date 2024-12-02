package com.example.aagApp.services;

import com.example.aagApp.entity.Prize;
import com.example.aagApp.entity.Team;
import com.example.aagApp.entity.Tournament;
import com.example.aagApp.model.PrizeDto;
import com.example.aagApp.model.TeamDto;
import com.example.aagApp.model.TournamentDto;
import com.example.aagApp.repository.PrizeRepo;
import com.example.aagApp.repository.TeamRepo;
import com.example.aagApp.repository.TournamentRepo;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class TournamentService {
    @Autowired
    private TournamentRepo tournamentRepo;
    @Autowired
    private TeamRepo teamRepo;

    @Autowired
    private PrizeRepo prizeRepo;

    public ResponseEntity<List<Tournament>> getAllTournaments() {
        System.out.println(" Service getAllTournaments");
        List<Tournament> tournaments = tournamentRepo.findAll();
        System.out.println("tournaments");
        System.out.println(tournaments);
        return ResponseEntity.ok(tournaments);
    }

    public ResponseEntity<Object> createTournament(TournamentDto tournamentDto) {
        System.out.println(" Service createTournaments");
        if (tournamentRepo.existsByName(tournamentDto.getName())) {
            return ResponseEntity.internalServerError().body("Tournament Name already existed");
        }
        Tournament save = tournamentRepo.save(tournamentDto.getTournament());
        return ResponseEntity.ok(save);
    }


    public ResponseEntity<Object> getTournamentById(Long tournamentId) {
        System.out.println(" Service getTournamentById");
        if (tournamentRepo.existsById(tournamentId)) {
            return ResponseEntity.ok(tournamentRepo.findById(tournamentId));
        } else return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tournament Not Found");
    }


    public ResponseEntity<Object> deleteTournament(Long tournamentId) {
        if (tournamentRepo.existsById(tournamentId)) {
            tournamentRepo.deleteById(tournamentId);
            return ResponseEntity.ok("Tournament Deleted Successfully");
        } else return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tournament Not Exists");
    }

    public ResponseEntity<?> endTournament(Long tournamentId) {

        if (!tournamentRepo.existsById(tournamentId)) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Tournament Not Exists");
        }

        Tournament tournament = tournamentRepo.findById(tournamentId).get();
        if (!tournament.getStatus().equals("ONGOING")) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Tournament must be ongoing to be ended.");
        }

        tournament.setStatus("COMPLETED");
        tournamentRepo.save(tournament);
        return ResponseEntity.ok("Tournament Ended");
    }

    public ResponseEntity<Object> getAllTeams(Long tournamentId) {
        if (tournamentRepo.existsById(tournamentId)) {
            return ResponseEntity.ok(teamRepo.findByTournamentId(tournamentId));
        } else return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tournament Not Exists");
    }

    public ResponseEntity<Object> addTeamToTournament(Long tournamentId, TeamDto teamDto) {
        System.out.println("Service addTeamToTour");
        if (tournamentRepo.existsById(tournamentId)) {
            if (teamRepo.existsByTeamId(teamDto.getTeamId())) {
                Team team1 = teamRepo.findByTeamId(teamDto.getTeamId());
                Tournament tournament = tournamentRepo.findById(tournamentId).get();
                team1.setTournament(tournament);
                teamRepo.save(team1);
                return ResponseEntity.ok("Team Added Successfully ");
            } else return ResponseEntity.badRequest().body("Team not existed");
        } else return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tournament Not Exists");
    }

    public ResponseEntity<Object> removeTeamFromTournament(Long tournamentId, Long teamId) {

        if (tournamentRepo.existsById(tournamentId)) {
            if (teamRepo.existsByTeamId(teamId)) {
                Team team = teamRepo.findByTeamId(teamId);
                team.setTournament(null);
//                System.out.println(team.getTeamId() + " " + team.getName());
                teamRepo.save(team);
                return ResponseEntity.ok("Team Removed From the Tournament "+ tournamentRepo.findById(tournamentId).get().getName());
            } else return ResponseEntity.badRequest().body("Team with teamId " + teamId + " not exists");
        } else return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tournament Not Exists");
    }


    public ResponseEntity<?> getOngoingTournaments() {
        List<Tournament> tournaments = tournamentRepo.findByStatus("ONGOING");
        if(tournaments.size()==0){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No ONGOING Tournament Present");
        }
        return ResponseEntity.ok(tournamentRepo.findByStatus("ONGOING"));
    }

    public ResponseEntity<?> getUpcomingTournaments() {
        List<Tournament> tournaments = tournamentRepo.findByStatus("ONGOING");
        if(tournaments.size()==0){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Upcoming Tournament Found");
        }
        return ResponseEntity.ok(tournamentRepo.findByStatus("UPCOMING"));
    }

    public ResponseEntity<?> distributePrize(@Valid @RequestBody PrizeDto prize) {

            if(!tournamentRepo.existsById(prize.getTournamentId())){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tournament Not Found");
            }
        Tournament tournament = tournamentRepo.findById(prize.getTournamentId()).get();
            if(tournament.getStatus()=="ONGOING" || tournament.getStatus()=="UPCOMING"){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Tournament Not Ended");
            }
            Team team = teamRepo.findByTeamId(prize.getTeamId());
            Prize savePrize = new Prize();
            savePrize.setTournament(tournament);
            savePrize.setTeam(team);
            savePrize.setPrizeName(prize.getPrizeName());
            savePrize.setAmount(prize.getAmount());
            prizeRepo.save(savePrize);
            return ResponseEntity.ok("Prize Distributed Successfully");

    }


}
