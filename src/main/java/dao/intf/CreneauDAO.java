package dao.intf;

import modele.Creneau;

import java.util.List;

/**
 * Created by VTanchereau on 28/06/2017.
 */
public interface CreneauDAO extends DAO<Creneau> {

    Creneau findById(int id);
    List<Creneau> findAll();
    Creneau insert(Creneau creneau);
    Creneau update(Creneau creneau);
    int delete(Creneau creneau);
}
