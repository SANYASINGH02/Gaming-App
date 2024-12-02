package com.example.aagApp.repository;

import com.example.aagApp.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerRepo extends JpaRepository<Player, Long> {
    Player findByTeamId(Long teamId);
    List<Player> findAllByTeamId(Long teamId);

    boolean existsByTeamId(Long teamId);
}
