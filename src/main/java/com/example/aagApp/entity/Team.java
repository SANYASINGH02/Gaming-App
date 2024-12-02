package com.example.aagApp.entity;

import com.example.aagApp.repository.TournamentRepo;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(unique = true,nullable = false)
    private Long teamId;
    @Column(nullable = false)
    private String name;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "leagueId",nullable = true)
//    @JsonBackReference  // Prevent League from being serialized in Team
    private League league;

   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(referencedColumnName = "id",name = "tournamentId",nullable = true)
   private Tournament tournament;


}
