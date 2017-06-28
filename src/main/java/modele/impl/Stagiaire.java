package modele.impl;

import modele.intf.ICreneau;
import modele.intf.IFinancementStagiaire;
import modele.intf.IStagiaire;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by VTanchereau on 27/06/2017.
 */
public class Stagiaire extends Personne implements IStagiaire{

    private List<IFinancementStagiaire> listFinancementsStagiaires;
    private List<ICreneau> listCreneaux;

    public Stagiaire(int id, String nom, String prenom, boolean interne) {
        super(id, nom, prenom, interne);
        this.listFinancementsStagiaires = new ArrayList<>();
        this.listCreneaux = new ArrayList<>();
    }

    public Stagiaire(String nom, String prenom, boolean interne) {
        super(nom, prenom, interne);
        this.listFinancementsStagiaires = new ArrayList<>();
        this.listCreneaux = new ArrayList<>();
    }

    public Stagiaire(int id, String nom, String prenom) {
        super(id, nom, prenom);
        this.listFinancementsStagiaires = new ArrayList<>();
        this.listCreneaux = new ArrayList<>();
    }

    public Stagiaire(String nom, String prenom) {
        super(nom, prenom);
        this.listFinancementsStagiaires = new ArrayList<>();
        this.listCreneaux = new ArrayList<>();
    }

    public List<IFinancementStagiaire> getListFinancementsStagiaires() {
        return listFinancementsStagiaires;
    }

    public void setListFinancementsStagiaires(List<IFinancementStagiaire> listFinancementsStagiaires) {
        this.listFinancementsStagiaires = listFinancementsStagiaires;
    }

    public List<ICreneau> getListCreneaux() {
        return listCreneaux;
    }

    public void setListCreneaux(List<ICreneau> listCreneaux) {
        this.listCreneaux = listCreneaux;
    }
}
