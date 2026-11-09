package tn.esprit.spring.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import tn.esprit.spring.domain.Equipement;
import tn.esprit.spring.repository.EquipementRepository;

import java.util.List;

@Service
public class EquipementService implements IEquipementService {

    private final EquipementRepository equipementRepository;

    public EquipementService(EquipementRepository equipementRepository) {
        this.equipementRepository = equipementRepository;
    }

    @Override
    public Equipement creer(Equipement equipement) {
        return equipementRepository.save(equipement);
    }

    @Override
    public Equipement obtenirParId(long id) {
        return equipementRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Equipement introuvable avec l'identifiant " + id));
    }

    @Override
    public List<Equipement> listerTous() {
        return equipementRepository.findAll();
    }

    @Override
    public Equipement modifier(long id, Equipement equipement) {
        Equipement existant = obtenirParId(id);
        existant.setLibelle(equipement.getLibelle());
        return equipementRepository.save(existant);
    }

    @Override
    public void supprimer(long id) {
        obtenirParId(id);
        equipementRepository.deleteById(id);
    }
}
