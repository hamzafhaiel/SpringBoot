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
import tn.esprit.spring.domain.Vehicule;
import tn.esprit.spring.service.VehiculeService;

import java.util.List;

@RestController
@RequestMapping("/vehicules")
@Tag(name = "Véhicules", description = "Gestion de la flotte de véhicules")
public class VehiculeController {

    private final VehiculeService vehiculeService;

    public VehiculeController(VehiculeService vehiculeService) {
        this.vehiculeService = vehiculeService;
    }

    @PostMapping
    @Operation(summary = "Créer un véhicule")
    public ResponseEntity<Vehicule> creer(@RequestBody Vehicule vehicule) {
        return ResponseEntity.status(HttpStatus.CREATED).body(vehiculeService.creer(vehicule));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Chercher un véhicule par identifiant")
    public Vehicule obtenirParId(@PathVariable long id) {
        return vehiculeService.obtenirParId(id);
    }

    @GetMapping("/listall")
    @Operation(summary = "Lister les véhicules")
    public List<Vehicule> listerTous() {
        return vehiculeService.listerTous();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modifier un véhicule")
    public ResponseEntity<Vehicule> modifier(@PathVariable long id, @RequestBody Vehicule vehicule) {
        return ResponseEntity.ok(vehiculeService.modifier(id, vehicule));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer un véhicule")
    public ResponseEntity<Void> supprimer(@PathVariable long id) {
        vehiculeService.supprimer(id);
        return ResponseEntity.noContent().build();
    }
}
