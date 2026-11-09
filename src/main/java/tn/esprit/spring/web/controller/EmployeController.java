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
import tn.esprit.spring.domain.Employe;
import tn.esprit.spring.service.IEmployeService;

import java.util.List;

@RestController
@RequestMapping("/employes")
@Tag(name = "Employés", description = "Gestion des employés des agences")
public class EmployeController {

    private final IEmployeService employeService;

    public EmployeController(IEmployeService employeService) {
        this.employeService = employeService;
    }

    @GetMapping("/test")
    @Operation(summary = "Tester le contrôleur", description = "Vérifie que le service web Employe répond.")
    public String testerController() {
        return "Le contrôleur Employe fonctionne.";
    }

    @PostMapping
    @Operation(summary = "Créer un employé")
    public ResponseEntity<Employe> creer(@RequestBody Employe employe) {
        return ResponseEntity.status(HttpStatus.CREATED).body(employeService.creer(employe));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Chercher un employé par identifiant")
    public Employe obtenirParId(@PathVariable long id) {
        return employeService.obtenirParId(id);
    }

    @GetMapping("/listall")
    @Operation(summary = "Lister les employés")
    public List<Employe> listerTous() {
        return employeService.listerTous();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modifier un employé")
    public ResponseEntity<Employe> modifier(@PathVariable long id, @RequestBody Employe employe) {
        return ResponseEntity.ok(employeService.modifier(id, employe));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer un employé")
    public ResponseEntity<Void> supprimer(@PathVariable long id) {
        employeService.supprimer(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/affecterAgence/{idAgence}/{idEmploye}")
    @Operation(summary = "Affecter un employé à une agence")
    public Employe affecterAgenceEmploye(@PathVariable long idAgence, @PathVariable long idEmploye) {
        return employeService.affecterAgenceEmploye(idAgence, idEmploye);
    }
}
