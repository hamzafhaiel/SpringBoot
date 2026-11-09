package tn.esprit.spring.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import tn.esprit.spring.domain.Contrat;
import tn.esprit.spring.repository.ContratRepository;

import java.util.List;

@Service
public class ContratService implements IContratService {

    private final ContratRepository contratRepository;

    public ContratService(ContratRepository contratRepository) {
        this.contratRepository = contratRepository;
    }

    @Override
    public Contrat creer(Contrat contrat) {
        return contratRepository.save(contrat);
    }

    @Override
    public Contrat obtenirParId(long id) {
        return contratRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Contrat introuvable avec l'identifiant " + id));
    }

    @Override
    public List<Contrat> listerTous() {
        return contratRepository.findAll();
    }

    @Override
    public Contrat modifier(long id, Contrat contrat) {
        Contrat existant = obtenirParId(id);
        existant.setDateSignature(contrat.getDateSignature());
        existant.setMontantTotal(contrat.getMontantTotal());
        existant.setValide(contrat.isValide());
        return contratRepository.save(existant);
    }

    @Override
    public void supprimer(long id) {
        obtenirParId(id);
        contratRepository.deleteById(id);
    }
}
