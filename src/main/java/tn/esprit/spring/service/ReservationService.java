package tn.esprit.spring.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import tn.esprit.spring.domain.Reservation;
import tn.esprit.spring.repository.ReservationRepository;

import java.util.List;

@Service
public class ReservationService implements IReservationService {

    private final ReservationRepository reservationRepository;

    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public Reservation creer(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    @Override
    public Reservation ajouterReservationContrat(Reservation r) {
        return reservationRepository.save(r);
    }

    public Reservation obtenirParId(long id) {
        return reservationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Reservation introuvable avec l'identifiant " + id));
    }

    public List<Reservation> listerTous() {
        return reservationRepository.findAll();
    }

    public Reservation modifier(long id, Reservation reservation) {
        Reservation existante = obtenirParId(id);
        existante.setDateDebut(reservation.getDateDebut());
        existante.setDateFin(reservation.getDateFin());
        existante.setStatut(reservation.getStatut());
        return reservationRepository.save(existante);
    }

    public void supprimer(long id) {
        obtenirParId(id);
        reservationRepository.deleteById(id);
    }
}
