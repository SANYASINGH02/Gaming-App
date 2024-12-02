package com.example.aagApp.repository;

import com.example.aagApp.entity.Team;
import com.example.aagApp.entity.Tournament;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeamRepo extends JpaRepository<Team,Long> {

    List<Team> findByTournamentId(Long tournamentId);
    boolean existsByTeamId(Long teamId);

    Team findByTeamId(Long teamId);
//    List<Team> findAll();
}
