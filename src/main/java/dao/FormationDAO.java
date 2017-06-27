package dao;

import modele.Formation;

import java.util.List;

/**
 * Created by VTanchereau on 27/06/2017.
 */
public interface FormationDAO {

    Formation findById(int id);
    List<Formation> findAll();
    Formation insert(Formation formation);
    Formation update(Formation formation);
    int delete(Formation formation);
}
