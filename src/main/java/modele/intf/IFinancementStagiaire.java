package modele.intf;

import java.time.LocalDate;

/**
 * Created by VTanchereau on 28/06/2017.
 */
public interface IFinancementStagiaire {

    IStagiaire getStagiaire();
    void setStagiaire(IStagiaire stagiaire);
    IFinancement getFinancement();
    void setFinancement(IFinancement financement);
    LocalDate getDateDebut();
    void setDateDebut(LocalDate dateDebut);
    LocalDate getDateFin();
    void setDateFin(LocalDate dateFin);
}
