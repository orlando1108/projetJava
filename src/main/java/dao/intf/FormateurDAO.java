package dao.intf;

import modele.impl.Formateur;

import java.util.List;

/**
 * Created by VTanchereau on 27/06/2017.
 */
public interface FormateurDAO extends DAO<Formateur> {
    Formateur findById(int id);
    List<Formateur> findAll();
    Formateur insert(Formateur formateur);
    Formateur update(Formateur formateur);
    int delete(Formateur formateur);
}
