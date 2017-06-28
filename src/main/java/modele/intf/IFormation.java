package modele.intf;

/**
 * Created by VTanchereau on 28/06/2017.
 */
public interface IFormation {
    int getId();
    void setId(int id);
    String getNom();
    void setNom(String nom);
    ISpecialite getSpecialite();
    void setSpecialite(ISpecialite specialite);
    IObjectif getObjectif();
    void setObjectif(IObjectif objectif);
}
