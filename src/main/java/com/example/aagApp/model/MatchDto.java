package com.example.aagApp.model;

import java.time.LocalDate;

import com.example.aagApp.entity.Match;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MatchDto {
//    private Long id;
    private String type;   // tournament or league
    private Long typeId;
    private Long team1Id;
    private Long team2Id;
    private LocalDate date;
    private String result;


}
