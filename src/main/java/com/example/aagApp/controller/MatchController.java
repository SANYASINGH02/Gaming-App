package com.example.aagApp.controller;

import com.example.aagApp.entity.Match;
import com.example.aagApp.model.MatchDto;
import com.example.aagApp.services.MatchService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/match")
public class MatchController {

    @Autowired
    MatchService matchService;

    @PostMapping("/create")
    public ResponseEntity<?> createMatch(@Valid @RequestBody MatchDto matchDto) {
        return matchService.createMatch(matchDto);
    }

    @GetMapping("/getAll")
    public List<Match> getAllMatches() {
       return matchService.getAllMatches();
    }


    @GetMapping("/get/{id}")
    public ResponseEntity<?> getMatchById(@RequestParam(value="matchId") Long id) {
        return matchService.getMatchById(id);
    }

    @PutMapping("/setResult")
    public ResponseEntity<?> setResult(@RequestParam(value="matchId") Long id, @RequestParam(value="result") String result){
        return matchService.setResult(id,result);
    }

}
