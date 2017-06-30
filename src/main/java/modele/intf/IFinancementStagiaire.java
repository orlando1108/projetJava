package modele.intf;

import java.time.LocalDate;

import modele.exception.ModelException;

/**
 * Created by VTanchereau on 28/06/2017.
 */
public interface IFinancementStagiaire {

    IStagiaire getStagiaire();
    void setStagiaire(IStagiaire stagiaire);
    IFinancement getFinancement();
    void setFinancement(IFinancement financement);
    LocalDate getDateDebut();
    void setDateDebut(LocalDate dateDebut)throws ModelException;
    LocalDate getDateFin();
    void setDateFin(LocalDate dateFin)throws ModelException;
}
