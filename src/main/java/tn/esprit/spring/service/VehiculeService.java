package tn.esprit.spring.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import tn.esprit.spring.domain.Equipement;
import tn.esprit.spring.domain.Vehicule;
import tn.esprit.spring.repository.EquipementRepository;
import tn.esprit.spring.repository.VehiculeRepository;

import java.util.List;

@Service
public class VehiculeService implements IVehiculeService {

    private final VehiculeRepository vehiculeRepository;
    private final EquipementRepository equipementRepository;

    public VehiculeService(VehiculeRepository vehiculeRepository, EquipementRepository equipementRepository) {
        this.vehiculeRepository = vehiculeRepository;
        this.equipementRepository = equipementRepository;
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

    @Override
    public Vehicule affecterEquipementVehicule(long idv, long ideq) {
        Vehicule vehicule = vehiculeRepository.findById(idv).orElse(null);
        Equipement equipement = equipementRepository.findById(ideq).orElse(null);

        assert vehicule != null;
        assert equipement != null;
        vehicule.getEquipements().add(equipement);
        return vehiculeRepository.save(vehicule);
    }
}
