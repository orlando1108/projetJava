package modele.impl;
import modele.intf.IFormation;
import modele.intf.IObjectif;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by VTanchereau on 27/06/2017.
 */
public class Objectif implements IObjectif{

    private int id;
    private String libelle;
    private List<IFormation> listFormations;

    public Objectif(String libelle) {
        this.libelle = libelle;
        this.listFormations = new ArrayList<>();
    }

    public Objectif(int id, String libelle) {
        this.id = id;
        this.libelle = libelle;
        this.listFormations = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public List<IFormation> getListFormations() {
        return listFormations;
    }

    public void setListFormations(List<IFormation> listFormations) {

        this.listFormations = listFormations;
    }

    @Override
    public String toString() {
        return "Objectif{" +
                "id=" + id +
                ", libelle='" + libelle + '\'' +
                '}';
    }
}
