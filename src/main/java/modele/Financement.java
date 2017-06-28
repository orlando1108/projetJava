package modele;

/**
 * Created by VTanchereau on 27/06/2017.
 */
public class Financement {
    private int id;
    private String libelle;

    public Financement(int idFinancement, String libelle) {
        this.id = idFinancement;
        this.libelle = libelle;
    }

    public Financement(String libelle) {
        this.libelle = libelle;
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
}
