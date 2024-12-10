package tn.esprit.project_springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.esprit.project_springboot.entities.Ticket;
import tn.esprit.project_springboot.entities.TrancheAge;
import tn.esprit.project_springboot.entities.TypeTicket;
import java.time.LocalDate;
import java.util.List;

public interface ITicketRepository extends JpaRepository<Ticket, Integer> {

    Long countByInternaute_TrancheAgeAndEvenement_DateEvenementBetween(
            TrancheAge trancheAge,
            LocalDate dateMin,
            LocalDate dateMax
    );

    @Query("SELECT SUM(t.prixTicket) FROM Ticket t " +
            "JOIN t.evenement e " +
            "WHERE e.nomEvenement = :nomEvt AND t.typeTicket = :typeTicket")
    Double montantRecupereParEvtEtTypeTicket(@Param("nomEvt") String nomEvt,
                                             @Param("typeTicket") TypeTicket typeTicket);


    @Query("SELECT t.internaute.idInternaute FROM Ticket t " +
            "GROUP BY t.internaute.idInternaute " +
            "ORDER BY COUNT(t.idTicket) DESC")
    List<Long> findInternauteIdAvecPlusDeTickets();

}
