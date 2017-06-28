package modele.impl;

/**
 * Created by VTanchereau on 27/06/2017.
 */
public class Personne {

    protected int id;
    protected String nom;
    protected String prenom;
    protected boolean interne;

    public Personne(int id, String nom, String prenom, boolean interne) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.interne = interne;
    }

    public Personne(String nom, String prenom, boolean interne) {
        this.nom = nom;
        this.prenom = prenom;
        this.interne = interne;
    }

    public Personne(int id, String nom, String prenom) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.interne = true;
    }

    public Personne(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
        this.interne = true;
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

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public boolean isInterne() {
        return interne;
    }

    public void setInterne(boolean interne) {
        this.interne = interne;
    }
}
