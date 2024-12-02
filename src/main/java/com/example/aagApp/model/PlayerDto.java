package com.example.aagApp.model;

import com.example.aagApp.entity.Player;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerDto {
    private String name;
    private String country;
    private String state;
    private String city;
    private int mobile;
    private Long teamId;
    private String teamName;

    public Player getPlayer() {
        Player player = new Player();
        player.setName(name);
        player.setCountry(country);
        player.setState(state);
        player.setCity(city);
        player.setMobile(mobile);
        return player;
    }
}
