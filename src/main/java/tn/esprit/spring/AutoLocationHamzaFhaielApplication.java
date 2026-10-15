package tn.esprit.spring;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import tn.esprit.spring.domain.CategorieVehicule;
import tn.esprit.spring.domain.StatutVehicule;
import tn.esprit.spring.domain.Vehicule;
import tn.esprit.spring.repository.VehiculeRepository;

import java.math.BigDecimal;

@SpringBootApplication
public class AutoLocationHamzaFhaielApplication {

    public static void main(String[] args) {
        SpringApplication.run(AutoLocationHamzaFhaielApplication.class, args);
    }

    @Bean
    CommandLineRunner initDatabase(VehiculeRepository vehiculeRepository) {
        return args -> {

            if (vehiculeRepository.count() == 0) {

                Vehicule vehicule1 = Vehicule.builder()
                        .immatriculation("123-TUN-456")
                        .marque("Renault")
                        .modele("Clio")
                        .categorie(CategorieVehicule.CITADINE)
                        .tarifJournalier(new BigDecimal("80.00"))
                        .statut(StatutVehicule.DISPONIBLE)
                        .build();

                Vehicule vehicule2 = Vehicule.builder()
                        .immatriculation("789-TUN-123")
                        .marque("Peugeot")
                        .modele("308")
                        .categorie(CategorieVehicule.BERLINE)
                        .tarifJournalier(new BigDecimal("120.00"))
                        .statut(StatutVehicule.DISPONIBLE)
                        .build();

                Vehicule vehicule3 = Vehicule.builder()
                        .immatriculation("456-TUN-789")
                        .marque("Dacia")
                        .modele("Duster")
                        .categorie(CategorieVehicule.SUV)
                        .tarifJournalier(new BigDecimal("150.00"))
                        .statut(StatutVehicule.MAINTENANCE)
                        .build();

                vehiculeRepository.save(vehicule1);
                vehiculeRepository.save(vehicule2);
                vehiculeRepository.save(vehicule3);
            }
        };
    }
}
