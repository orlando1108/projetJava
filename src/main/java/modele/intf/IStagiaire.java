package modele.intf;

/**
 * Created by VTanchereau on 28/06/2017.
 */
public interface IStagiaire {

    int getId();

    void setId(int id);

    String getNom();

    void setNom(String nom);

    String getPrenom();

    void setPrenom(String prenom);

    boolean isInterne();

    void setInterne(boolean interne);
}
