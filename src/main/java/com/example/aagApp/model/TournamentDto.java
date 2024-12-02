package com.example.aagApp.model;

import com.example.aagApp.entity.Tournament;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TournamentDto {
//    private Long id;
    private String name;
    private String status;  // Upcoming,ONGOING,Completed

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate startDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate endDate;
    public Tournament getTournament() {
        Tournament tournament = new Tournament();
        tournament.setName(name);
        tournament.setStatus(status);
        tournament.setStartDate(startDate);
        tournament.setEndDate(endDate);
        return tournament;
    }


}
