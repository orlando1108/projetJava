package dao;

import modele.Formateur;

import java.util.List;

/**
 * Created by VTanchereau on 27/06/2017.
 */
public interface FormateurDAO {
    Formateur findById(int id);
    List<Formateur> findAll();
    Formateur insert(Formateur formateur);
    Formateur update(Formateur formateur);
    int delete(Formateur formateur);
}
