package dao.intf;

import java.util.List;

/**
 * Created by VTanchereau on 28/06/2017.
 */
public interface DAO <T> {

    T findById(int id);
    List<T> findAll();
    T insert(T formateur);
    T update(T formateur);
    int delete(T formateur);
}
