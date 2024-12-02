package com.example.aagApp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class League {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

//    @JsonManagedReference // Serialize League and its Teams
   @OneToMany(mappedBy = "league", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Team> teams;

}
