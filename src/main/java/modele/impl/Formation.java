package modele.impl;

import modele.impl.Objectif;
import modele.impl.Specialite;
import modele.intf.IFormation;
import modele.intf.IObjectif;
import modele.intf.ISpecialite;

/**
 * Created by VTanchereau on 27/06/2017.
 */
public class Formation implements IFormation {
    private int id;
    private String nom;
    private ISpecialite specialite;
    private IObjectif objectif;

    public Formation(int id, String nom, ISpecialite specialite, IObjectif objectif) {
        this.id = id;
        this.nom = nom;
        this.specialite = specialite;
        this.objectif = objectif;
    }

    public Formation(String nom, ISpecialite specialite, IObjectif objectif) {
        this.nom = nom;
        this.specialite = specialite;
        this.objectif = objectif;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public ISpecialite getSpecialite() {
        return specialite;
    }

    public void setSpecialite(ISpecialite specialite) {
        this.specialite = specialite;
    }

    public IObjectif getObjectif() {
        return objectif;
    }

    public void setObjectif(IObjectif objectif) {
        this.objectif = objectif;
    }
}
