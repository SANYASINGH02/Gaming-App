package com.example.aagApp.controller;

import com.example.aagApp.entity.Player;
import com.example.aagApp.model.PlayerDto;
import com.example.aagApp.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/player")
public class PlayerController {

    @Autowired
    private PlayerService playerService;


    @PostMapping("/create")
    public ResponseEntity<?> createPlayer(@RequestBody PlayerDto playerDto) {
        return playerService.createPlayer(playerDto);
    }

    @GetMapping("/getAll")
    public List<Player> getAllPlayers() {
        return playerService.getAllPlayers();
    }

    @GetMapping("/getByTeam/{teamId}")
    public ResponseEntity<List<Player>> getPlayersByTeamId(@PathVariable Long teamId) {
        List<Player> players = playerService.getPlayersByTeamId(teamId);
        return ResponseEntity.ok(players);
    }

    @GetMapping("/get/{Id}")
    public ResponseEntity<?> getPlayersById(@PathVariable Long id) {
        return playerService.getPlayersById(id);
    }

    @PutMapping("/update/{playerId}")
    public ResponseEntity<?> updatePlayer(@PathVariable Long playerId, @RequestBody PlayerDto playerDto) {
        return playerService.updatePlayer(playerId, playerDto);
    }

    @DeleteMapping("/delete/{playerId}")
    public ResponseEntity<String> deletePlayer(@PathVariable Long playerId) {
        playerService.deletePlayer(playerId);
        return ResponseEntity.ok("Deletion Successful");
    }
}

