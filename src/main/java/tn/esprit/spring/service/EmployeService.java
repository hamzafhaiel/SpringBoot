package tn.esprit.spring.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import tn.esprit.spring.domain.Agence;
import tn.esprit.spring.domain.Employe;
import tn.esprit.spring.repository.AgenceRepository;
import tn.esprit.spring.repository.EmployeRepository;

import java.util.List;

@Service
public class EmployeService implements IEmployeService {

    private final EmployeRepository employeRepository;
    private final AgenceRepository agenceRepository;

    public EmployeService(EmployeRepository employeRepository, AgenceRepository agenceRepository) {
        this.employeRepository = employeRepository;
        this.agenceRepository = agenceRepository;
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
        obtenirParId(id);
        employeRepository.deleteById(id);
    }

    @Override
    public Employe affecterAgenceEmploye(long idAgence, long idEmploye) {
        Employe employe = employeRepository.findById(idEmploye).orElse(null);
        Agence agence = agenceRepository.findById(idAgence).orElse(null);

        assert employe != null;
        employe.setAgence(agence);
        return employeRepository.save(employe);
    }
}
