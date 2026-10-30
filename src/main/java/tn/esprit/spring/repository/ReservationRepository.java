package tn.esprit.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.spring.domain.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
