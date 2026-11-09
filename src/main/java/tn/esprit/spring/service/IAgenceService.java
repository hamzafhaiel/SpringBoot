package tn.esprit.spring.service;

import tn.esprit.spring.domain.Agence;
import java.util.List;

public interface IAgenceService {
    Agence creer(Agence agence);
    Agence obtenirParId(long id);
    List<Agence> listerTous();
    Agence modifier(long id, Agence agence);
    void supprimer(long id);
}
