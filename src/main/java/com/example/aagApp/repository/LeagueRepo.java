package com.example.aagApp.repository;

import com.example.aagApp.entity.League;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeagueRepo extends JpaRepository<League,Long> {

     String findNameById(Long id);
}
