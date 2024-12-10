package tn.esprit.project_springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.project_springboot.entities.Internaute;

import java.util.Optional;

public interface IInternauteRepository extends JpaRepository<Internaute, Integer> {

}
