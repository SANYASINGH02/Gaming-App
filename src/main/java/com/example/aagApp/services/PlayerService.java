package com.example.aagApp.services;

import com.example.aagApp.entity.Player;
import com.example.aagApp.entity.Team;
import com.example.aagApp.model.PlayerDto;
import com.example.aagApp.repository.PlayerRepo;
import com.example.aagApp.repository.TeamRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerService {

    @Autowired
    private PlayerRepo playerRepo;

    @Autowired
    private TeamRepo teamRepo;

    public ResponseEntity<?> createPlayer(PlayerDto playerDto) {
        Team team = teamRepo.findByTeamId(playerDto.getTeamId());
        if(!teamRepo.existsByTeamId(team.getTeamId())){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Team Not Found.");
        }
        Player player = playerDto.getPlayer();
        player.setTeam(team);
        playerRepo.save(player);
        return ResponseEntity.ok("Player Created Successfully");
    }

    public List<Player> getAllPlayers() {
        return playerRepo.findAll();
    }

    public List<Player> getPlayersByTeamId(Long teamId) {

        List<Player> players = playerRepo.findAllByTeamId(teamId);

        return players;
    }

    public ResponseEntity<?> getPlayersById(Long id) {
        if(playerRepo.existsById(id)){
            return ResponseEntity.ok(playerRepo.findById(id));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Player Not Found.");
    }


    public ResponseEntity<?> updatePlayer(Long playerId, PlayerDto updatedPlayer) {
        if(!playerRepo.existsById(playerId)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Player Not Found.");
        }
        Player player = playerRepo.findById(playerId).get();
        player.setName(updatedPlayer.getName());
        player.setCountry(updatedPlayer.getCountry());
        player.setCity(updatedPlayer.getCity());
        player.setState(updatedPlayer.getState());
        player.setMobile(updatedPlayer.getMobile());
        playerRepo.save(player);
        return ResponseEntity.ok("Player Updated ! ");
    }

    public void deletePlayer(Long playerId) {
        playerRepo.deleteById(playerId);
    }
}
