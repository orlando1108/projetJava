package dao.intf;

import modele.impl.Specialite;

import java.util.List;

/**
 * Created by VTanchereau on 27/06/2017.
 */
public interface SpecialiteDAO extends DAO<Specialite> {
    Specialite findById(int id);
    List<Specialite> findAll();
    Specialite insert(Specialite specialite);
    Specialite update(Specialite specialite);
    int delete(Specialite specialite);
}
