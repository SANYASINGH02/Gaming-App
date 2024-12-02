package com.example.aagApp.entity;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String country;
    private String state;
    private String city;
    @Column(nullable = false)
    private int mobile;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(referencedColumnName = "teamId",name = "teamId",unique = false,nullable = false)
    private Team team;


}