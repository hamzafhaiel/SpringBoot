package tn.esprit.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.spring.domain.Employe;

public interface EmployeRepository extends JpaRepository<Employe, Long> {
}
