package modele;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by VTanchereau on 27/06/2017.
 */
public class Stagiaire extends Personne{

    public Stagiaire(int id, String nom, String prenom, boolean interne) {
        super(id, nom, prenom, interne);
    }

    public Stagiaire(String nom, String prenom, boolean interne) {
        super(nom, prenom, interne);
    }

    public Stagiaire(int id, String nom, String prenom) {
        super(id, nom, prenom);
    }

    public Stagiaire(String nom, String prenom) {
        super(nom, prenom);
    }

}
