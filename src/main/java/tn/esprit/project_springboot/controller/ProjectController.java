package tn.esprit.project_springboot.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.project_springboot.entities.*;
import tn.esprit.project_springboot.service.IServiceproject;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProjectController  {
    private final IServiceproject serviceproject;

    @PostMapping("/ajouterInternaute")
    public Internaute ajouterInternaute(@RequestBody Internaute internaute){
        return serviceproject.ajouterInternaute(internaute);
    }
    @PostMapping("/ajouterEvenement")
    public Evenement ajouterEvenement(@RequestBody Evenement evenement){
        return serviceproject.ajouterEvenement(evenement);
    }
    @PostMapping("/ajouterTicketsEtAffecterAevenementEtInternaute")
    public List<Ticket> ajouterTicketsEtAffecterAevenementEtInternaute(@RequestBody List<Ticket> tickets,@RequestBody Long idEvenement, @RequestBody int idInternaute){
        return serviceproject.ajouterTicketsEtAffecterAevenementEtInternaute(tickets, idEvenement, idInternaute);
    }
    @GetMapping("/count-internautes-adulte")
    public Long nbInternauteParTrancheAgeEtDateEvenement(TrancheAge trancheAge, LocalDate dateMin, LocalDate dateMax){
        return serviceproject.nbInternauteParTrancheAgeEtDateEvenement(
                TrancheAge.ADULTE,
                LocalDate.of(2024, 8, 1),
                LocalDate.of(2024, 10, 1)
        );
    }
    @GetMapping("/montantRecupereParEvtEtTypeTicket")
    public Double montantRecupereParEvtEtTypeTicket(String nomEvt, TypeTicket typeTicket){
        return serviceproject.montantRecupereParEvtEtTypeTicket(nomEvt, typeTicket);
    }

    @GetMapping("/InternauteLePlusActif")
    public String getInternauteLePlusActif() {
        return serviceproject.internauteLePlusAcrtif();
    }
}
