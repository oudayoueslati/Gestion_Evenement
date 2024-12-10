package tn.esprit.project_springboot.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.project_springboot.entities.Categorie;


public interface ICategoryRepository extends JpaRepository<Categorie, Long> {

    Categorie findByCodeCategorie(String codeCategorie);
}
