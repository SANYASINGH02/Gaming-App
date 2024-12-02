package com.example.aagApp.repository;

import com.example.aagApp.entity.Tournament;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TournamentRepo extends JpaRepository<Tournament,Long> {
//    boolean existsByShortName(String shortName);
    boolean existsByName(String name);

    List<Tournament> findByStatus(String status);
}




