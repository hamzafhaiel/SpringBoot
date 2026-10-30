package tn.esprit.spring.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import tn.esprit.spring.domain.Client;
import tn.esprit.spring.repository.ClientRepository;

import java.util.List;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client creer(Client client) {
        return clientRepository.save(client);
    }

    public Client obtenirParId(long id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Client introuvable avec l'identifiant " + id));
    }

    public List<Client> listerTous() {
        return clientRepository.findAll();
    }

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

    public void supprimer(long id) {
        obtenirParId(id);
        clientRepository.deleteById(id);
    }
}
