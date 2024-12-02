package com.example.aagApp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name="matches")
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String type;  //tournament or league

    @Column(nullable = false)
    private Long typeId;  //tournament or league id

//    @ManyToOne
//    @JoinColumn(name = "tournamentId",nullable = false)
//    private Tournament tournament;

    @ManyToOne
    @JoinColumn(referencedColumnName = "teamId",name = "team1Id",nullable = false)
    private Team team1;

    @ManyToOne
    @JoinColumn(referencedColumnName = "teamId",name = "team2Id",nullable = false)
    private Team team2;

    @Column(nullable = false)
    private LocalDate date;

    private String result;  // team name
}
