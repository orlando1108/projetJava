package modele;

/**
 * Created by VTanchereau on 27/06/2017.
 */
public class Specialite {
    private int id;
    private String nom;
    private String code;

    public Specialite(int idSpecialite, String nom, String code) {
        this.id = idSpecialite;
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

    public void setId(int idSpecialite) {
        this.id = idSpecialite;
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
