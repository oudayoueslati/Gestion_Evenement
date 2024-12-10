package tn.esprit.project_springboot.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Evenement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEvenement;
    private String nomEvenement;
    private Long nbPlacesRestants;
    private LocalDate dateEvenement;
    @OneToMany(mappedBy = "evenement")
    Set<Ticket> tickets;
    @ManyToMany
    Set<Categorie> categories;

}
