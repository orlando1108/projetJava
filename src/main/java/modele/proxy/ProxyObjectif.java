package modele.proxy;

import dao.ObjectifDAO;
import dao.mysql.ObjectifDAOImpl;
import modele.impl.Objectif;
import modele.intf.IObjectif;

/**
 * Created by VTanchereau on 28/06/2017.
 */
public class ProxyObjectif implements IObjectif{

    private int id;
    private Objectif instance;

    public ProxyObjectif(int _id){
        this.id = _id;
    }

    private void createInstance(){
        // TODO récupérer grâce a la factory
        ObjectifDAO dao = new ObjectifDAOImpl();
        this.instance = dao.findById(this.id);
    }

    public int getId() {
        return this.id;
    }

    @Override
    public String getLibelle() {
        if (this.instance == null){
            this.createInstance();
        }

        return this.instance.getLibelle();
    }

    @Override
    public void setLibelle(String libelle) {
        if (this.instance == null){
            this.createInstance();
        }

        this.instance.setLibelle(libelle);
    }
}
