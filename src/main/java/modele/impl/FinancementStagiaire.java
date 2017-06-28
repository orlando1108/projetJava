package modele.impl;

import modele.intf.IFinancement;
import modele.intf.IFinancementStagiaire;
import modele.intf.IStagiaire;

import java.time.LocalDate;
/**
 * Created by VTanchereau on 28/06/2017.
 */
public class FinancementStagiaire implements IFinancementStagiaire {

    private IStagiaire stagiaire;
    private IFinancement financement;
    private LocalDate dateDebut;
    private LocalDate dateFin;

    public FinancementStagiaire(IStagiaire stagiaire, IFinancement financement, LocalDate dateDebut, LocalDate dateFin) {
        this.stagiaire = stagiaire;
        this.financement = financement;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
    }

    public FinancementStagiaire(IStagiaire stagiaire, IFinancement financement, LocalDate dateDebut) {
        this.stagiaire = stagiaire;
        this.financement = financement;
        this.dateDebut = dateDebut;
    }

    public IStagiaire getStagiaire() {
        return stagiaire;
    }

    public void setStagiaire(IStagiaire stagiaire) {
        this.stagiaire = stagiaire;
    }

    public IFinancement getFinancement() {
        return financement;
    }

    public void setFinancement(IFinancement financement) {
        this.financement = financement;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }
}
