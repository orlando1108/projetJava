package modele.impl;

import modele.intf.IFinancement;
import modele.intf.IStagiaire;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by VTanchereau on 27/06/2017.
 */
public class Financement implements IFinancement {
    private int id;
    private String libelle;
    private List<IStagiaire> listStagiaires;

    public Financement(int idFinancement, String libelle) {
        this.id = idFinancement;
        this.libelle = libelle;
        this.listStagiaires = new ArrayList<>();
    }

    public Financement(String libelle) {
        this.libelle = libelle;
        this.listStagiaires = new ArrayList<>();
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

    public List<IStagiaire> getListStagiaires() {
        return listStagiaires;
    }

    public void setListStagiaires(List<IStagiaire> listStagiaires) {
        this.listStagiaires = listStagiaires;
    }
}
