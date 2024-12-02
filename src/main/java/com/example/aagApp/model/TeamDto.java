package com.example.aagApp.model;

import com.example.aagApp.entity.Team;
import jakarta.validation.constraints.NotBlank;
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
public class TeamDto {
    @NotBlank
    private Long id;
    @NotBlank
    private Long teamId;
    @NotBlank
    private String name;
    private Long leagueId;
    private String leagueName;
    private Long tournamentId;
    private String tournamentName;




    public Team getTeam(){
        Team team = new Team();
        team.setTeamId(teamId);
        team.setName(name);
        return team;
    }

}
