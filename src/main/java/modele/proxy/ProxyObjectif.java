package modele.proxy;

import dao.intf.DAO;
import dao.DAOFactory;
import dao.DAONames;
import modele.impl.Objectif;
import modele.intf.IObjectif;

/**
 * Created by VTanchereau on 28/06/2017.
 */
public class ProxyObjectif implements IObjectif{

    private int id;
    private Objectif instance;

    public ProxyObjectif(int id){
        this.id = id;
        this.instance = null;
    }

    private void createInstance(){
        // TODO récupérer grâce a la factory
        DAO<Objectif> dao = DAOFactory.getDAO(DAONames.Objectif);
        this.instance = dao.findById(this.id);
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
        if (this.instance == null){
            this.createInstance();
        }

        this.instance.setId(id);
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
