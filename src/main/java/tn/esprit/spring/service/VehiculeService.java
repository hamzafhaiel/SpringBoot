package tn.esprit.spring.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import tn.esprit.spring.domain.Vehicule;
import tn.esprit.spring.repository.VehiculeRepository;

import java.util.List;

@Service
public class VehiculeService {

    private final VehiculeRepository vehiculeRepository;

    public VehiculeService(VehiculeRepository vehiculeRepository) {
        this.vehiculeRepository = vehiculeRepository;
    }

    public Vehicule creer(Vehicule vehicule) {
        return vehiculeRepository.save(vehicule);
    }

    public Vehicule obtenirParId(long id) {
        return vehiculeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Vehicule introuvable avec l'identifiant " + id));
    }

    public List<Vehicule> listerTous() {
        return vehiculeRepository.findAll();
    }

    public Vehicule modifier(long id, Vehicule vehicule) {
        Vehicule existant = obtenirParId(id);
        existant.setImmatriculation(vehicule.getImmatriculation());
        existant.setMarque(vehicule.getMarque());
        existant.setModele(vehicule.getModele());
        existant.setCategorie(vehicule.getCategorie());
        existant.setTarifJournalier(vehicule.getTarifJournalier());
        existant.setStatut(vehicule.getStatut());
        return vehiculeRepository.save(existant);
    }

    public void supprimer(long id) {
        obtenirParId(id);
        vehiculeRepository.deleteById(id);
    }
}
