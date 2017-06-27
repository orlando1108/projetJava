package modele;

/**
 * Created by VTanchereau on 27/06/2017.
 */
public class Formateur extends Personne{

    public Formateur(int id, String nom, String prenom, boolean interne) {
        super(id, nom, prenom, interne);
    }

    public Formateur(String nom, String prenom, boolean interne) {
        super(nom, prenom, interne);
    }

    public Formateur(int id, String nom, String prenom) {
        super(id, nom, prenom);
    }

    public Formateur(String nom, String prenom) {
        super(nom, prenom);
    }

}
