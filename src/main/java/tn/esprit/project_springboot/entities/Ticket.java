package tn.esprit.project_springboot.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTicket;
    private String codeTicket;
    private Double prixTicket;
    @Enumerated(EnumType.STRING)
    private TypeTicket typeTicket;
    @ManyToOne
    Internaute internaute;
    @ManyToOne
    Evenement evenement;
}
