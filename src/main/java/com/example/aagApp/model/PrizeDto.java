package com.example.aagApp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Component
public class PrizeDto {
    private Long tournamentId;
    private Long teamId;
    private String prizeName; // e.g., "Champion", "Runner-up"
    private Double amount;
}