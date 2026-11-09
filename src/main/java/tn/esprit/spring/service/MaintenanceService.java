package tn.esprit.spring.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import tn.esprit.spring.domain.Maintenance;
import tn.esprit.spring.domain.Vehicule;
import tn.esprit.spring.repository.MaintenanceRepository;
import tn.esprit.spring.repository.VehiculeRepository;

import java.util.List;

@Service
public class MaintenanceService implements IMaintenanceService {

    private final MaintenanceRepository maintenanceRepository;
    private final VehiculeRepository vehiculeRepository;

    public MaintenanceService(MaintenanceRepository maintenanceRepository, VehiculeRepository vehiculeRepository) {
        this.maintenanceRepository = maintenanceRepository;
        this.vehiculeRepository = vehiculeRepository;
    }

    @Override
    public Maintenance creer(Maintenance maintenance) {
        return maintenanceRepository.save(maintenance);
    }

    @Override
    public Maintenance obtenirParId(long id) {
        return maintenanceRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Maintenance introuvable avec l'identifiant " + id));
    }

    @Override
    public List<Maintenance> listerTous() {
        return maintenanceRepository.findAll();
    }

    @Override
    public Maintenance modifier(long id, Maintenance maintenance) {
        Maintenance existante = obtenirParId(id);
        existante.setDateDebut(maintenance.getDateDebut());
        existante.setDateFin(maintenance.getDateFin());
        existante.setDescription(maintenance.getDescription());
        return maintenanceRepository.save(existante);
    }

    @Override
    public void supprimer(long id) {
        obtenirParId(id);
        maintenanceRepository.deleteById(id);
    }

    @Override
    public Maintenance affecterMaintenanceVehicule(long idm, long idV) {
        Maintenance maintenance = maintenanceRepository.findById(idm).orElse(null);
        Vehicule vehicule = vehiculeRepository.findById(idV).orElse(null);

        assert maintenance != null;
        assert vehicule != null;
        maintenance.setVehicule(vehicule);
        return maintenanceRepository.save(maintenance);
    }
}
