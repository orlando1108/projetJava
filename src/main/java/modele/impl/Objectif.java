package modele.impl;

import modele.intf.IObjectif;

/**
 * Created by VTanchereau on 27/06/2017.
 */
public class Objectif implements IObjectif{

    private String libelle;

    public Objectif(String libelle) {
        this.libelle = libelle;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
    
}
