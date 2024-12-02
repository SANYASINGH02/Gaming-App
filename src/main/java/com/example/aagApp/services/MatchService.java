package com.example.aagApp.services;

import com.example.aagApp.entity.Match;
import com.example.aagApp.entity.Team;
import com.example.aagApp.entity.Tournament;
import com.example.aagApp.model.MatchDto;
import com.example.aagApp.repository.MatchRepo;
import com.example.aagApp.repository.TeamRepo;
import com.example.aagApp.repository.TournamentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatchService {

    @Autowired
    private MatchRepo matchRepo;

    @Autowired
    private TeamRepo teamRepo;

    @Autowired
    private TournamentRepo tournamentRepo;

    public ResponseEntity<?> createMatch(MatchDto matchDto) {


        String type = matchDto.getType();
        if(type == "tournament"){

            if(!tournamentRepo.existsById(matchDto.getTypeId()) || !teamRepo.existsByTeamId(matchDto.getTeam1Id())
            || !teamRepo.existsByTeamId(matchDto.getTeam2Id())){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid tournament or team IDs.");
            }
        }
        else{
            if(!teamRepo.existsById(matchDto.getTypeId()) || !teamRepo.existsByTeamId(matchDto.getTeam1Id())
                    || !teamRepo.existsByTeamId(matchDto.getTeam2Id())){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid tournament or team IDs.");
            }
        }

        Team team1 = teamRepo.findByTeamId(matchDto.getTeam1Id());
        Team team2 = teamRepo.findByTeamId(matchDto.getTeam2Id());

        Match match = new Match();
        match.setType(matchDto.getType());
        match.setTypeId(matchDto.getTypeId());
        match.setTeam1(team1);
        match.setTeam2(team2);
        match.setDate(matchDto.getDate());
        match.setResult(matchDto.getResult());

        matchRepo.save(match);
        return ResponseEntity.ok("Match Created Successfully" + match);
    }



    public List<Match> getAllMatches() {
        return matchRepo.findAll();
    }


    public ResponseEntity<?> getMatchById(Long id) {

        if(id==null){
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Match Id cannot be Null");
        } else if (!matchRepo.existsById(id)) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Match Doesn't exist");
        }

        return ResponseEntity.ok(matchRepo.findById(id).get());

}

    public ResponseEntity<?> setResult(Long id, String result) {
        if(id==null){
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Match Id cannot be Null");
        } else if (!matchRepo.existsById(id)) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Match Doesn't exist");

        }
        Match match = matchRepo.findById(id).get();
        match.setResult(result);
        matchRepo.save(match);
        return ResponseEntity.ok("Result Declared " + match);
    }

}
