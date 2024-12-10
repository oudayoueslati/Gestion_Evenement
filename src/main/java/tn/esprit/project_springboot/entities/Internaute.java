package tn.esprit.project_springboot.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Internaute {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idInternaute;
    private Long identifiant;
    @Enumerated(EnumType.STRING)
    private TrancheAge trancheAge;
    @OneToMany(mappedBy = "internaute")
    Set<Ticket> tickets;
}
