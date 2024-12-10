package tn.esprit.project_springboot.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import tn.esprit.project_springboot.entities.*;
import tn.esprit.project_springboot.repositories.*;

import java.time.LocalDate;
import java.util.*;

@Slf4j
@RequiredArgsConstructor
@Service
public class ServiceprojectImpl implements IServiceproject{

    private final ICategoryRepository categoryRepository;
    private final IEvenementRepository evenementRepository;
    private final IInternauteRepository internauteRepository;
    private final ITicketRepository ticketRepository;

    @Override
    public Internaute ajouterInternaute(Internaute internaute) {
        return internauteRepository.save(internaute);
    }

    @Override
    public Evenement ajouterEvenement(Evenement evenement) {
        List<Categorie> list = new ArrayList<>();
        if(evenement.getCategories() != null) {
            for (Categorie categorie : evenement.getCategories()) {
                Categorie c = categoryRepository.findByCodeCategorie(categorie.getCodeCategorie());
                if(c != null) {
                    list.add(c);
                }
            }
            evenement.setCategories(new HashSet<> (list));
        }
        return evenementRepository.save(evenement) ;
    }

    @Scheduled(cron = "*/15 * * * * *")
    @Override
    public void listeEvenementsParCategorie() {

        List<Evenement> evenements = evenementRepository.findAll();
        evenements.forEach(evenement -> {
            evenement.getCategories().forEach(categorie -> {
                log.info("Categorie: {}", categorie.getNomCategorie());
                log.info("    Evenement: {} planifié le {}",
                        evenement.getNomEvenement(),
                        evenement.getDateEvenement());
            });
        });
    }

    @Override
    public List<Ticket> ajouterTicketsEtAffecterAevenementEtInternaute(List<Ticket> tickets, Long idEvenement, int idInternaute) {
        Evenement evenement=evenementRepository.findById(idEvenement).orElse(null);
        Internaute internaute=internauteRepository.findById(idInternaute).orElse(null);

        if (tickets.size() > evenement.getNbPlacesRestants()) {
            throw new java.lang.UnsupportedOperationException("Nombre de places demandées indisponibles !");
        }
        tickets.forEach(ticket -> {
            ticket.setEvenement(evenement);
            ticket.setInternaute(internaute);
        });
        evenement.setNbPlacesRestants(evenement.getNbPlacesRestants() - tickets.size());

        ticketRepository.saveAll(tickets);
        evenementRepository.save(evenement);
        return tickets;
    }

    @Override
    public Long nbInternauteParTrancheAgeEtDateEvenement(TrancheAge trancheAge, LocalDate dateMin, LocalDate dateMax) {
        return ticketRepository.countByInternaute_TrancheAgeAndEvenement_DateEvenementBetween(trancheAge, dateMin, dateMax);
    }

    @Override
    public Double montantRecupereParEvtEtTypeTicket(String nomEvt, TypeTicket typeTicket) {
        return ticketRepository.montantRecupereParEvtEtTypeTicket(nomEvt, typeTicket);
    }

    @Override
    public String internauteLePlusAcrtif() {
        List<Long> ids = ticketRepository.findInternauteIdAvecPlusDeTickets();
        return ids.isEmpty() ? null : ids.get(0).toString();
    }

}



