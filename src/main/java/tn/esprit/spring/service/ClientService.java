package tn.esprit.spring.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.spring.domain.Client;
import tn.esprit.spring.domain.Reservation;
import tn.esprit.spring.repository.ClientRepository;
import tn.esprit.spring.repository.ReservationRepository;

import java.util.List;

@Service
public class ClientService implements IClientService {

    private final ClientRepository clientRepository;
    private final ReservationRepository reservationRepository;

    public ClientService(ClientRepository clientRepository, ReservationRepository reservationRepository) {
        this.clientRepository = clientRepository;
        this.reservationRepository = reservationRepository;
    }

    @Override
    public Client creer(Client client) {
        return clientRepository.save(client);
    }

    @Override
    @Transactional
    public Client ajouterReservationClient(Client c) {
        c = clientRepository.save(c);
        for (Reservation r : c.getReservations()) {
            r.setClient(c);
            reservationRepository.save(r);
        }
        return c;
    }

    @Override
    public Client obtenirParId(long id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Client introuvable avec l'identifiant " + id));
    }

    @Override
    public List<Client> listerTous() {
        return clientRepository.findAll();
    }

    @Override
    public Client modifier(long id, Client client) {
        Client existant = obtenirParId(id);
        existant.setNom(client.getNom());
        existant.setPrenom(client.getPrenom());
        existant.setEmail(client.getEmail());
        existant.setTelephone(client.getTelephone());
        existant.setNumPermis(client.getNumPermis());
        existant.setDateInscription(client.getDateInscription());
        return clientRepository.save(existant);
    }

    @Override
    public void supprimer(long id) {
        obtenirParId(id);
        clientRepository.deleteById(id);
    }
}
