package tn.esprit.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.spring.domain.Paiement;

public interface PaiementRepository extends JpaRepository<Paiement, Long> {
}
