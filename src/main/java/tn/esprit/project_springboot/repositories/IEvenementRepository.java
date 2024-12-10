package tn.esprit.project_springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.project_springboot.entities.Evenement;

public interface IEvenementRepository extends JpaRepository<Evenement, Long> {
}
