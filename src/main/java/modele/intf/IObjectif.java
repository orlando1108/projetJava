package modele.intf;

import java.util.List;

/**
 * Created by VTanchereau on 28/06/2017.
 */
public interface IObjectif {

    int getId();
    void setId(int id);
    String getLibelle();
    void setLibelle(String libelle);
    List<IFormation> getListFormations();
    void setListFormations(List<IFormation> listFormations);
}
