package tn.esprit.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.spring.domain.Agence;

public interface AgenceRepository extends JpaRepository<Agence, Long> {
}
