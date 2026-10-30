package tn.esprit.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.spring.domain.Equipement;

public interface EquipementRepository extends JpaRepository<Equipement, Long> {
}
