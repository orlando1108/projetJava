package dao.intf;

import modele.impl.Objectif;

import java.util.List;

/**
 * Created by VTanchereau on 27/06/2017.
 */
public interface ObjectifDAO extends DAO<Objectif> {

    Objectif findById(int id);
    List<Objectif> findAll();
    Objectif insert(Objectif objectif);
    Objectif update(Objectif objectif);
    int delete(Objectif objectif);
}
