package dao;

import modele.Objectif;

import java.util.List;

/**
 * Created by VTanchereau on 27/06/2017.
 */
public interface ObjectifDAO {

    Objectif selectById(int id);
    List<Objectif> selectAll();
    Objectif insert(Objectif objectif);
    Objectif update(Objectif objectif);
    int delete(Objectif objectif);
}
