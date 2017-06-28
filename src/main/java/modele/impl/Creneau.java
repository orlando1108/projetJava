package modele.impl;

import modele.impl.Formation;
import modele.intf.ICreneau;
import modele.intf.IFormateur;
import modele.intf.IFormation;
import modele.intf.IStagiaire;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by VTanchereau on 28/06/2017.
 */
public class Creneau implements ICreneau{

    private int id;
    private LocalDateTime dateDebut;
    private LocalDateTime dateFin;
    private boolean interne;
    private IFormation formation;
    private List<IStagiaire> listStagiaires;
    private List<IFormateur> listFormateurs;

    public Creneau(int id, LocalDateTime dateDebut, LocalDateTime dateFin, boolean interne, IFormation formation) {
        this.id = id;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.interne = interne;
        this.formation = formation;
        this.listStagiaires = new ArrayList<>();
        this.listFormateurs = new ArrayList<>();
    }

    public Creneau(LocalDateTime dateDebut, LocalDateTime dateFin, boolean interne, IFormation formation) {
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.interne = interne;
        this.formation = formation;
        this.listStagiaires = new ArrayList<>();
        this.listFormateurs = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDateTime dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDateTime getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDateTime dateFin) {
        this.dateFin = dateFin;
    }

    public boolean isInterne() {
        return interne;
    }

    public void setInterne(boolean interne) {
        this.interne = interne;
    }

    public IFormation getFormation() {
        return formation;
    }

    public void setFormation(IFormation formation) {
        this.formation = formation;
    }

    public List<IStagiaire> getListStagiaires() {
        return listStagiaires;
    }

    public void setListStagiaires(List<IStagiaire> listStagiaires) {
        this.listStagiaires = listStagiaires;
    }

    public List<IFormateur> getListFormateurs() {
        return listFormateurs;
    }

    public void setListFormateurs(List<IFormateur> listFormateurs) {
        this.listFormateurs = listFormateurs;
    }
}
