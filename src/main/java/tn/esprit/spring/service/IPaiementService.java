package tn.esprit.spring.service;

import tn.esprit.spring.domain.Paiement;
import java.util.List;

public interface IPaiementService {
    Paiement creer(Paiement paiement);
    Paiement obtenirParId(long id);
    List<Paiement> listerTous();
    Paiement modifier(long id, Paiement paiement);
    void supprimer(long id);
}
