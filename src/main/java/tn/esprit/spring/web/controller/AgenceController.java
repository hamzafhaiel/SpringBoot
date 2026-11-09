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
import tn.esprit.spring.domain.Agence;
import tn.esprit.spring.service.IAgenceService;

import java.util.List;

@RestController
@RequestMapping("/agences")
@Tag(name = "Agences", description = "Gestion des agences de location")
public class AgenceController {

    private final IAgenceService agenceService;

    public AgenceController(IAgenceService agenceService) {
        this.agenceService = agenceService;
    }

    @PostMapping
    @Operation(summary = "Créer une agence")
    public ResponseEntity<Agence> creer(@RequestBody Agence agence) {
        return ResponseEntity.status(HttpStatus.CREATED).body(agenceService.creer(agence));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Chercher une agence par identifiant")
    public Agence obtenirParId(@PathVariable long id) {
        return agenceService.obtenirParId(id);
    }

    @GetMapping("/listall")
    @Operation(summary = "Lister les agences")
    public List<Agence> listerTous() {
        return agenceService.listerTous();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modifier une agence")
    public ResponseEntity<Agence> modifier(@PathVariable long id, @RequestBody Agence agence) {
        return ResponseEntity.ok(agenceService.modifier(id, agence));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer une agence")
    public ResponseEntity<Void> supprimer(@PathVariable long id) {
        agenceService.supprimer(id);
        return ResponseEntity.noContent().build();
    }
}
