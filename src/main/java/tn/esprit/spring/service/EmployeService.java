package tn.esprit.spring.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import tn.esprit.spring.domain.Employe;
import tn.esprit.spring.repository.EmployeRepository;

import java.util.List;

@Service
public class EmployeService {

    private final EmployeRepository employeRepository;

    public EmployeService(EmployeRepository employeRepository) {
        this.employeRepository = employeRepository;
    }

    public Employe creer(Employe employe) {
        return employeRepository.save(employe);
    }

    public Employe obtenirParId(long id) {
        return employeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Employé introuvable avec l'identifiant " + id));
    }

    public List<Employe> listerTous() {
        return employeRepository.findAll();
    }

    public Employe modifier(long id, Employe employe) {
        Employe employeExistant = obtenirParId(id);
        employeExistant.setNom(employe.getNom());
        employeExistant.setPrenom(employe.getPrenom());
        employeExistant.setRole(employe.getRole());
        return employeRepository.save(employeExistant);
    }

    public void supprimer(long id) {
        employeRepository.deleteById(id);
    }
}
