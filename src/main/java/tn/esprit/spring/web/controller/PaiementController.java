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
import tn.esprit.spring.domain.Paiement;
import tn.esprit.spring.service.IPaiementService;

import java.util.List;

@RestController
@RequestMapping("/paiements")
@Tag(name = "Paiements", description = "Gestion des paiements")
public class PaiementController {

    private final IPaiementService paiementService;

    public PaiementController(IPaiementService paiementService) {
        this.paiementService = paiementService;
    }

    @PostMapping
    @Operation(summary = "Créer un paiement")
    public ResponseEntity<Paiement> creer(@RequestBody Paiement paiement) {
        return ResponseEntity.status(HttpStatus.CREATED).body(paiementService.creer(paiement));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Chercher un paiement par identifiant")
    public Paiement obtenirParId(@PathVariable long id) {
        return paiementService.obtenirParId(id);
    }

    @GetMapping("/listall")
    @Operation(summary = "Lister les paiements")
    public List<Paiement> listerTous() {
        return paiementService.listerTous();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modifier un paiement")
    public ResponseEntity<Paiement> modifier(@PathVariable long id, @RequestBody Paiement paiement) {
        return ResponseEntity.ok(paiementService.modifier(id, paiement));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer un paiement")
    public ResponseEntity<Void> supprimer(@PathVariable long id) {
        paiementService.supprimer(id);
        return ResponseEntity.noContent().build();
    }
}
