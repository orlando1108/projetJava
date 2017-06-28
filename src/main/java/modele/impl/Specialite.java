package modele.impl;

import modele.intf.ISpecialite;

/**
 * Created by VTanchereau on 27/06/2017.
 */
public class Specialite implements ISpecialite {
    private int id;
    private String nom;
    private String code;

    public Specialite(int id, String nom, String code) {
        this.id = id;
        this.nom = nom;
        this.code = code;
    }

    public Specialite(String nom, String code) {
        this.nom = nom;
        this.code = code;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
