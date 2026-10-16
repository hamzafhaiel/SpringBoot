package tn.esprit.spring.web.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.spring.domain.Employe;
import tn.esprit.spring.service.EmployeService;

import java.util.List;

@RestController
@RequestMapping("/employes")
public class EmployeController {

    private final EmployeService employeService;

    public EmployeController(EmployeService employeService) {
        this.employeService = employeService;
    }

    @GetMapping("/test")
    public String testerController() {
        return "Le contrôleur Employe fonctionne.";
    }

    @PostMapping
    public Employe creer(@RequestBody Employe employe) {
        return employeService.creer(employe);
    }

    @GetMapping("/{id}")
    public Employe obtenirParId(@PathVariable long id) {
        return employeService.obtenirParId(id);
    }

    @GetMapping
    public List<Employe> listerTous() {
        return employeService.listerTous();
    }

    @PutMapping("/{id}")
    public Employe modifier(@PathVariable long id, @RequestBody Employe employe) {
        return employeService.modifier(id, employe);
    }

    @DeleteMapping("/{id}")
    public void supprimer(@PathVariable long id) {
        employeService.supprimer(id);
    }
}
