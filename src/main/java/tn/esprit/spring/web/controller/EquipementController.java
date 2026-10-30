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
import tn.esprit.spring.domain.Equipement;
import tn.esprit.spring.service.EquipementService;

import java.util.List;

@RestController
@RequestMapping("/equipements")
@Tag(name = "Équipements", description = "Gestion des équipements")
public class EquipementController {

    private final EquipementService equipementService;

    public EquipementController(EquipementService equipementService) {
        this.equipementService = equipementService;
    }

    @PostMapping
    @Operation(summary = "Créer un équipement")
    public ResponseEntity<Equipement> creer(@RequestBody Equipement equipement) {
        return ResponseEntity.status(HttpStatus.CREATED).body(equipementService.creer(equipement));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Chercher un équipement par identifiant")
    public Equipement obtenirParId(@PathVariable long id) {
        return equipementService.obtenirParId(id);
    }

    @GetMapping("/listall")
    @Operation(summary = "Lister les équipements")
    public List<Equipement> listerTous() {
        return equipementService.listerTous();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modifier un équipement")
    public ResponseEntity<Equipement> modifier(@PathVariable long id, @RequestBody Equipement equipement) {
        return ResponseEntity.ok(equipementService.modifier(id, equipement));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer un équipement")
    public ResponseEntity<Void> supprimer(@PathVariable long id) {
        equipementService.supprimer(id);
        return ResponseEntity.noContent().build();
    }
}
