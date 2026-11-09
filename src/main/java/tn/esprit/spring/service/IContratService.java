package tn.esprit.spring.service;

import tn.esprit.spring.domain.Contrat;
import java.util.List;

public interface IContratService {
    Contrat creer(Contrat contrat);
    Contrat obtenirParId(long id);
    List<Contrat> listerTous();
    Contrat modifier(long id, Contrat contrat);
    void supprimer(long id);
}
