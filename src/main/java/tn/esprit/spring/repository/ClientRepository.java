package tn.esprit.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.spring.domain.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
