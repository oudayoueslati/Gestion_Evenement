package tn.esprit.project_springboot.service;

import tn.esprit.project_springboot.entities.*;

import java.time.LocalDate;
import java.util.List;

public interface IServiceproject {

    Internaute ajouterInternaute(Internaute internaute);
    Evenement ajouterEvenement(Evenement evenement);
    void listeEvenementsParCategorie();
    List<Ticket> ajouterTicketsEtAffecterAevenementEtInternaute(List<Ticket> tickets,Long idEvenement,int idInternaute);
    Long nbInternauteParTrancheAgeEtDateEvenement(TrancheAge trancheAge, LocalDate dateMin, LocalDate dateMax);
    Double montantRecupereParEvtEtTypeTicket(String nomEvt, TypeTicket typeTicket);
    String internauteLePlusAcrtif();

}
