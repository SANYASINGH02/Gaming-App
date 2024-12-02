package com.example.aagApp.services;

import com.example.aagApp.entity.League;
import com.example.aagApp.entity.Team;
import com.example.aagApp.model.LeagueDto;
import com.example.aagApp.repository.LeagueRepo;
import com.example.aagApp.repository.TeamRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LeagueService {

    @Autowired
    private LeagueRepo leagueRepo;

    @Autowired
    private TeamRepo teamRepo;
    public String createLeague(League league) {
        League savedLeague = leagueRepo.save(league);
        List<Team> teamList = league.getTeams();
        if(teamList != null){
            for (Team team : teamList){
                team.setLeague(savedLeague);
                teamRepo.save(team);
            }
        }
        return "League Created Successfully ";
    }

    public List<LeagueDto> getAllLeagues() {
        List<League> leagues = leagueRepo.findAll();
        List<LeagueDto> leagueDtos = new ArrayList<>();
        for (int i=0;i< leagues.size();i++)
        {
            LeagueDto leagueDto = new LeagueDto(leagues.get(i).getId(), leagues.get(i).getName());
            leagueDtos.add(leagueDto);
        }
        return leagueDtos;
    }

    public Optional<League> getLeagueById(Long id) {
        return leagueRepo.findById(id);
    }

    public void deleteLeague(Long id) {
        leagueRepo.deleteById(id);
    }

    public LeagueDto updateLeague(Long id, League updatedLeague) {
        League savedLeague = leagueRepo.findById(id).get();
        savedLeague.setName(updatedLeague.getName());

        List<Team> teamList = updatedLeague.getTeams();
        if(teamList != null){
            for (Team team : teamList){
                teamRepo.save(team);
                team.setLeague(updatedLeague);
                teamRepo.save(team);
            }
        }
        League league = leagueRepo.save(savedLeague);
        return new LeagueDto(league.getId(), league.getName());
    }

    public String addTeamToLeague(Long leagueId, Team team) {
        Optional<League> league = leagueRepo.findById(leagueId);
        if(league.isPresent()){
            if(teamRepo.existsByTeamId(team.getTeamId())){
                Team team1 = teamRepo.findByTeamId(team.getTeamId());
                team1.setLeague(league.get());
                teamRepo.save(team1);
                return "Team Added Successfully";
            }
            else return "Team Not Exist";
        }
        return "League Not Exist";
    }

    public String deleteTeamFromLeague(Long leagueId, Long teamId) {
        Optional<Team> team = teamRepo.findById(teamId);
        if(team.isEmpty())
            return "Team Not Present in the League";
        Team t1 = team.get();
        t1.setLeague(null);
        teamRepo.save(t1);
        return "Team Removed from the league";
    }

}
