package tn.esprit.spring.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import tn.esprit.spring.domain.Agence;
import tn.esprit.spring.repository.AgenceRepository;

import java.util.List;

@Service
public class AgenceService implements IAgenceService {

    private final AgenceRepository agenceRepository;

    public AgenceService(AgenceRepository agenceRepository) {
        this.agenceRepository = agenceRepository;
    }

    @Override
    public Agence creer(Agence agence) {
        return agenceRepository.save(agence);
    }

    @Override
    public Agence obtenirParId(long id) {
        return agenceRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Agence introuvable avec l'identifiant " + id));
    }

    @Override
    public List<Agence> listerTous() {
        return agenceRepository.findAll();
    }

    @Override
    public Agence modifier(long id, Agence agence) {
        Agence existante = obtenirParId(id);
        existante.setNom(agence.getNom());
        existante.setVille(agence.getVille());
        existante.setAdresse(agence.getAdresse());
        existante.setTelephone(agence.getTelephone());
        return agenceRepository.save(existante);
    }

    @Override
    public void supprimer(long id) {
        obtenirParId(id);
        agenceRepository.deleteById(id);
    }
}
