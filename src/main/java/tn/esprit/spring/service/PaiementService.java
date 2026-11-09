package tn.esprit.spring.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import tn.esprit.spring.domain.Paiement;
import tn.esprit.spring.repository.PaiementRepository;

import java.util.List;

@Service
public class PaiementService implements IPaiementService {

    private final PaiementRepository paiementRepository;

    public PaiementService(PaiementRepository paiementRepository) {
        this.paiementRepository = paiementRepository;
    }

    public Paiement creer(Paiement paiement) {
        return paiementRepository.save(paiement);
    }

    public Paiement obtenirParId(long id) {
        return paiementRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Paiement introuvable avec l'identifiant " + id));
    }

    public List<Paiement> listerTous() {
        return paiementRepository.findAll();
    }

    public Paiement modifier(long id, Paiement paiement) {
        Paiement existant = obtenirParId(id);
        existant.setMontant(paiement.getMontant());
        existant.setDatePaiement(paiement.getDatePaiement());
        existant.setModePaiement(paiement.getModePaiement());
        return paiementRepository.save(existant);
    }

    public void supprimer(long id) {
        obtenirParId(id);
        paiementRepository.deleteById(id);
    }
}
