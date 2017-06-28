package modele;

import modele.impl.Objectif;

/**
 * Created by VTanchereau on 27/06/2017.
 */
public class Formation {
    private int id;
    private String nom;
    private Specialite specialite;
    private Objectif objectif;

    public Formation(int id, String nom, Specialite specialite, Objectif objectif) {
        this.id = id;
        this.nom = nom;
        this.specialite = specialite;
        this.objectif = objectif;
    }

    public Formation(String nom, Specialite specialite, Objectif objectif) {
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

    public Specialite getSpecialite() {
        return specialite;
    }

    public void setSpecialite(Specialite specialite) {
        this.specialite = specialite;
    }

    public Objectif getObjectif() {
        return objectif;
    }

    public void setObjectif(Objectif objectif) {
        this.objectif = objectif;
    }
}
