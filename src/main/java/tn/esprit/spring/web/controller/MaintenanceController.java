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
import tn.esprit.spring.domain.Maintenance;
import tn.esprit.spring.service.MaintenanceService;

import java.util.List;

@RestController
@RequestMapping("/maintenances")
@Tag(name = "Maintenances", description = "Gestion des opérations de maintenance")
public class MaintenanceController {

    private final MaintenanceService maintenanceService;

    public MaintenanceController(MaintenanceService maintenanceService) {
        this.maintenanceService = maintenanceService;
    }

    @PostMapping
    @Operation(summary = "Créer une maintenance")
    public ResponseEntity<Maintenance> creer(@RequestBody Maintenance maintenance) {
        return ResponseEntity.status(HttpStatus.CREATED).body(maintenanceService.creer(maintenance));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Chercher une maintenance par identifiant")
    public Maintenance obtenirParId(@PathVariable long id) {
        return maintenanceService.obtenirParId(id);
    }

    @GetMapping("/listall")
    @Operation(summary = "Lister les maintenances")
    public List<Maintenance> listerTous() {
        return maintenanceService.listerTous();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modifier une maintenance")
    public ResponseEntity<Maintenance> modifier(@PathVariable long id, @RequestBody Maintenance maintenance) {
        return ResponseEntity.ok(maintenanceService.modifier(id, maintenance));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer une maintenance")
    public ResponseEntity<Void> supprimer(@PathVariable long id) {
        maintenanceService.supprimer(id);
        return ResponseEntity.noContent().build();
    }
}
