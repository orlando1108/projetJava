package modele.impl;

import modele.intf.IFinancement;
import modele.intf.IFinancementStagiaire;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by VTanchereau on 27/06/2017.
 */
public class Financement implements IFinancement {
    private int id;
    private String libelle;
    private List<IFinancementStagiaire> listFinancementsStagiaires;

    public Financement(int idFinancement, String libelle) {
        this.id = idFinancement;
        this.libelle = libelle;
        this.listFinancementsStagiaires = new ArrayList<>();
    }

    public Financement(String libelle) {
        this.libelle = libelle;
        this.listFinancementsStagiaires = new ArrayList<>();
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

    public List<IFinancementStagiaire> getListFinancementsStagiaires() {
        return listFinancementsStagiaires;
    }

    public void setListFinancementsStagiaires(List<IFinancementStagiaire> listStagiaires) {
        this.listFinancementsStagiaires = listStagiaires;
    }

    @Override
    public String toString() {
        return "Financement{" +
                "id=" + id +
                ", libelle='" + libelle + '\'' +
                '}';
    }
}
