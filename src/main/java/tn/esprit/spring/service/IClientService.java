package tn.esprit.spring.service;

import tn.esprit.spring.domain.Client;
import java.util.List;

public interface IClientService {
    Client creer(Client client);
    Client obtenirParId(long id);
    List<Client> listerTous();
    Client modifier(long id, Client client);
    void supprimer(long id);
    Client ajouterReservationClient(Client c);
}
