package dao.intf;

import modele.Financement;

import java.util.List;

/**
 * Created by VTanchereau on 28/06/2017.
 */
public interface FinancementDAO extends DAO<Financement> {
    Financement findById(int id);
    List<Financement> findAll();
    Financement insert(Financement financement);
    Financement update(Financement financement);
    int delete(Financement financement);
}
