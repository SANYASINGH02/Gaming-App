package com.example.aagApp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Component
public class LeagueDto {
    private Long id;
    private String name;
//    private Long leagueId;
//    private String leagueName;
}
