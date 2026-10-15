package tn.esprit.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.spring.domain.Vehicule;

public interface VehiculeRepository extends JpaRepository<Vehicule, Long> {
}