package tn.esprit.spring.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import tn.esprit.spring.domain.Maintenance;
import tn.esprit.spring.repository.MaintenanceRepository;

import java.util.List;

@Service
public class MaintenanceService {

    private final MaintenanceRepository maintenanceRepository;

    public MaintenanceService(MaintenanceRepository maintenanceRepository) {
        this.maintenanceRepository = maintenanceRepository;
    }

    public Maintenance creer(Maintenance maintenance) {
        return maintenanceRepository.save(maintenance);
    }

    public Maintenance obtenirParId(long id) {
        return maintenanceRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Maintenance introuvable avec l'identifiant " + id));
    }

    public List<Maintenance> listerTous() {
        return maintenanceRepository.findAll();
    }

    public Maintenance modifier(long id, Maintenance maintenance) {
        Maintenance existante = obtenirParId(id);
        existante.setDateDebut(maintenance.getDateDebut());
        existante.setDateFin(maintenance.getDateFin());
        existante.setDescription(maintenance.getDescription());
        return maintenanceRepository.save(existante);
    }

    public void supprimer(long id) {
        obtenirParId(id);
        maintenanceRepository.deleteById(id);
    }
}
