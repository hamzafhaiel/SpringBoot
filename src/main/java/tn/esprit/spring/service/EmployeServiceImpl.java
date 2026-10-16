package tn.esprit.spring.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import tn.esprit.spring.domain.Employe;
import tn.esprit.spring.repository.EmployeRepository;

import java.util.List;

@Service
public class EmployeServiceImpl implements IEmployeService {

    private final EmployeRepository employeRepository;

    public EmployeServiceImpl(EmployeRepository employeRepository) {
        this.employeRepository = employeRepository;
    }

    @Override
    public Employe creer(Employe employe) {
        return employeRepository.save(employe);
    }

    @Override
    public Employe obtenirParId(long id) {
        return employeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Employé introuvable avec l'identifiant " + id));
    }

    @Override
    public List<Employe> listerTous() {
        return employeRepository.findAll();
    }

    @Override
    public Employe modifier(long id, Employe employe) {
        Employe employeExistant = obtenirParId(id);
        employeExistant.setNom(employe.getNom());
        employeExistant.setPrenom(employe.getPrenom());
        employeExistant.setRole(employe.getRole());
        return employeRepository.save(employeExistant);
    }

    @Override
    public void supprimer(long id) {
        employeRepository.delete(obtenirParId(id));
    }
}
