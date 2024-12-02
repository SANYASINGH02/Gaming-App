package com.example.aagApp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Prize {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tournamentId", nullable = false)
    private Tournament tournament;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(referencedColumnName = "teamid",name = "teamId",nullable = false)
    private Team team;

    private String prizeName;   //Winner, Runner Up
    private Double amount;
}
