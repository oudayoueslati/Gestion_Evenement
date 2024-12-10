package tn.esprit.project_springboot.entities;

import jakarta.persistence.*;
import lombok.*;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Categorie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCategorie;
    private String nomCategorie;
    private String codeCategorie;
    @ManyToMany(mappedBy = "categories")
    Set<Evenement> evenements;

}
