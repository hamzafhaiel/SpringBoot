package tn.esprit.spring.service;

import tn.esprit.spring.domain.Employe;

import java.util.List;

public interface IEmployeService {

    Employe creer(Employe employe);

    Employe obtenirParId(long id);

    List<Employe> listerTous();

    Employe modifier(long id, Employe employe);

    void supprimer(long id);
}
