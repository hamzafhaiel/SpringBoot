package tn.esprit.spring.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseStatus;
import tn.esprit.spring.domain.Reservation;
import tn.esprit.spring.service.IReservationService;

import java.util.List;

@RestController
@RequestMapping("/reservations")
@Tag(name = "Réservations", description = "Gestion des réservations de véhicules")
public class ReservationController {

    private final IReservationService reservationService;

    public ReservationController(IReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping
    @Operation(summary = "Créer une réservation")
    public ResponseEntity<Reservation> creer(@RequestBody Reservation reservation) {
        return ResponseEntity.status(HttpStatus.CREATED).body(reservationService.creer(reservation));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Chercher une réservation par identifiant")
    public Reservation obtenirParId(@PathVariable long id) {
        return reservationService.obtenirParId(id);
    }

    @PostMapping({"/ajouterreservationcontrat", "/ajouterReservationContrat"})
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Créer une réservation avec son contrat")
    public Reservation ajouterReservationContrat(@RequestBody Reservation r) {
        return reservationService.ajouterReservationContrat(r);
    }

    @GetMapping("/listall")
    @Operation(summary = "Lister les réservations")
    public List<Reservation> listerTous() {
        return reservationService.listerTous();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modifier une réservation")
    public ResponseEntity<Reservation> modifier(@PathVariable long id, @RequestBody Reservation reservation) {
        return ResponseEntity.ok(reservationService.modifier(id, reservation));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer une réservation")
    public ResponseEntity<Void> supprimer(@PathVariable long id) {
        reservationService.supprimer(id);
        return ResponseEntity.noContent().build();
    }
}
