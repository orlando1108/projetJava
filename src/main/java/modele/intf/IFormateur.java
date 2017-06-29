package modele.intf;

import modele.impl.Creneau;

import java.util.List;

/**
 * Created by VTanchereau on 28/06/2017.
 */
public interface IFormateur extends IPersonne{

    List<ICreneau> getListCreneaux();
    void setListCreneaux(List<ICreneau> listCreneaux);
    boolean isInterne();
    void setInterne(boolean interne);
}
