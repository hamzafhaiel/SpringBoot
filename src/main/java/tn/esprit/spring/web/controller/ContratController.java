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
import tn.esprit.spring.domain.Contrat;
import tn.esprit.spring.service.ContratService;

import java.util.List;

@RestController
@RequestMapping("/contrats")
@Tag(name = "Contrats", description = "Gestion des contrats de location")
public class ContratController {

    private final ContratService contratService;

    public ContratController(ContratService contratService) {
        this.contratService = contratService;
    }

    @PostMapping
    @Operation(summary = "Créer un contrat")
    public ResponseEntity<Contrat> creer(@RequestBody Contrat contrat) {
        return ResponseEntity.status(HttpStatus.CREATED).body(contratService.creer(contrat));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Chercher un contrat par identifiant")
    public Contrat obtenirParId(@PathVariable long id) {
        return contratService.obtenirParId(id);
    }

    @GetMapping("/listall")
    @Operation(summary = "Lister les contrats")
    public List<Contrat> listerTous() {
        return contratService.listerTous();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modifier un contrat")
    public ResponseEntity<Contrat> modifier(@PathVariable long id, @RequestBody Contrat contrat) {
        return ResponseEntity.ok(contratService.modifier(id, contrat));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer un contrat")
    public ResponseEntity<Void> supprimer(@PathVariable long id) {
        contratService.supprimer(id);
        return ResponseEntity.noContent().build();
    }
}
