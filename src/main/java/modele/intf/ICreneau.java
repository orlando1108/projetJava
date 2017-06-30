package modele.intf;

import java.time.LocalDateTime;
import java.util.List;

import modele.exception.ModelException;

/**
 * Created by VTanchereau on 28/06/2017.
 */
public interface ICreneau {
    int getId();
    void setId(int id);
    LocalDateTime getDateDebut();
    void setDateDebut(LocalDateTime dateDebut)  throws ModelException;
    LocalDateTime getDateFin();
    void setDateFin(LocalDateTime dateFin)  throws ModelException;
    boolean isInterne();
    void setInterne(boolean interne);
    IFormation getFormation();
    void setFormation(IFormation formation);
    List<IStagiaire> getListStagiaires();
    void setListStagiaires(List<IStagiaire> listStagiaires)throws ModelException;
    List<IFormateur> getListFormateurs();
    void setListFormateurs(List<IFormateur> listFormateurs)throws ModelException;
}
