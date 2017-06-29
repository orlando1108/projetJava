package modele.impl;

import modele.intf.ICreneau;
import modele.intf.IFormateur;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by VTanchereau on 27/06/2017.
 */
public class Formateur extends Personne implements IFormateur{

    private List<ICreneau> listCreneaux;
    protected boolean interne;

    public Formateur(int id, String nom, String prenom, boolean interne) {
        super(id, nom, prenom);
        this.interne = interne;
        this.listCreneaux = new ArrayList<>();
    }

    public Formateur(String nom, String prenom, boolean interne) {
        super(nom, prenom);
        this.interne = interne;
        this.listCreneaux = new ArrayList<>();
    }

    public List<ICreneau> getListCreneaux() {
        return listCreneaux;
    }

    public void setListCreneaux(List<ICreneau> listCreneaux) {
        this.listCreneaux = listCreneaux;
    }

    public boolean isInterne() {
        return interne;
    }

    public void setInterne(boolean interne) {
        this.interne = interne;
    }

    public String toString(){
        return this.getPrenom() + " " + this.getNom();
    }
}
