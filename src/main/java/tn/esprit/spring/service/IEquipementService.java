package tn.esprit.spring.service;

import tn.esprit.spring.domain.Equipement;
import java.util.List;

public interface IEquipementService {
    Equipement creer(Equipement equipement);
    Equipement obtenirParId(long id);
    List<Equipement> listerTous();
    Equipement modifier(long id, Equipement equipement);
    void supprimer(long id);
}
