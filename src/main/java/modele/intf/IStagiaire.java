package modele.intf;

import java.util.List;

/**
 * Created by VTanchereau on 28/06/2017.
 */
public interface IStagiaire extends IPersonne{

    List<IFinancementStagiaire> getListFinancementsStagiaires();
    void setListFinancementsStagiaires(List<IFinancementStagiaire> listFinancementsStagiaires);
    List<ICreneau> getListCreneaux();
    void setListCreneaux(List<ICreneau> listCreneaux);
}
