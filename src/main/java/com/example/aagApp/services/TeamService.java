package com.example.aagApp.services;


import com.example.aagApp.entity.League;
import com.example.aagApp.entity.Tournament;
import com.example.aagApp.model.TeamDto;
import com.example.aagApp.entity.Team;
import com.example.aagApp.repository.LeagueRepo;
import com.example.aagApp.repository.TeamRepo;
import com.example.aagApp.repository.TournamentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TeamService {

    @Autowired
    private TeamRepo teamRepo;

    @Autowired
    private TournamentRepo tournamentRepo;

    @Autowired
    public LeagueRepo leagueRepo;

    
    public List<Team> getAllTeams() {
        return teamRepo.findAll();
}

    public ResponseEntity<?> getTeamById(Long id) {
        if (!teamRepo.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Team Not found");
        }
        Team team = teamRepo.findById(id).get();

        return ResponseEntity.ok(team);
    }

       public Team createTeam(TeamDto teamDto) {
           Team team = teamDto.getTeam();
           teamRepo.save(team);
           return team;
    }


    public ResponseEntity<?> updateTeam(Long teamId, Team updatedTeam) {
        Optional<Team> team1 = teamRepo.findById(teamId);
        if(team1.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Team Not found");
        }
        Team team = teamRepo.findById(teamId).get();
        team.setLeague(updatedTeam.getLeague());
        team.setName(updatedTeam.getName());
        League league = team.getLeague();
        if(league != null)
            league = leagueRepo.findById(team.getLeague().getId()).get();
        teamRepo.save(team);

        return ResponseEntity.status(HttpStatus.OK).body(team);
    }


    public String deleteTeam(Long teamId) {
        Optional<Team> team = teamRepo.findById(teamId); // Ensure team exist
        if(team.isEmpty()){
            return "Team Not Exist";
        }
        teamRepo.deleteById(teamId);
        return "Team Deleted Successfully";
    }

    private TeamDto teamDtoToTeam(Team team){
        Optional<Tournament> tournament = null;
        if(team.getTournament()!=null)
             tournament = tournamentRepo.findById(team.getTournament().getId());

        Optional<League> league = null;
        if(team.getLeague() != null)
           league = leagueRepo.findById(team.getLeague().getId());

        TeamDto teamDto = new TeamDto();
        teamDto.setId(team.getId());
        teamDto.setTeamId(team.getTeamId());
        teamDto.setName(team.getName());
        if(tournament!=null){
            teamDto.setTournamentId(tournament.get().getId());
            teamDto.setTournamentName(tournament.get().getName());
        }
        if(league!=null){
            teamDto.setLeagueId(league.get().getId());
            teamDto.setLeagueName(league.get().getName());
        }
        return teamDto;
    }

}
