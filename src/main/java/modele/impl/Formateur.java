package modele.impl;

import modele.intf.IFormateur;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by VTanchereau on 27/06/2017.
 */
public class Formateur extends Personne implements IFormateur{

    private List<Creneau> listCreneaux;

    public Formateur(int id, String nom, String prenom, boolean interne) {
        super(id, nom, prenom, interne);
        this.listCreneaux = new ArrayList<>();
    }

    public Formateur(String nom, String prenom, boolean interne) {
        super(nom, prenom, interne);
        this.listCreneaux = new ArrayList<>();
    }

    public Formateur(int id, String nom, String prenom) {
        super(id, nom, prenom);
        this.listCreneaux = new ArrayList<>();
    }

    public Formateur(String nom, String prenom) {
        super(nom, prenom);
        this.listCreneaux = new ArrayList<>();
    }

    public List<Creneau> getListCreneaux() {
        return listCreneaux;
    }

    public void setListCreneaux(List<Creneau> listCreneaux) {
        this.listCreneaux = listCreneaux;
    }
}
