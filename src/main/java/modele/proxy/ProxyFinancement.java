package modele.proxy;

import dao.DAOFactory;
import dao.DAONames;
import dao.intf.DAO;
import modele.impl.Financement;
import modele.intf.IFinancement;

/**
 * Created by VTanchereau on 28/06/2017.
 */
public class ProxyFinancement implements IFinancement{

    private int id;
    private Financement instance;

    public ProxyFinancement(int id) {
        this.id = id;
        this.instance = null;
    }

    private void getInstance(){
        if (instance == null) {
            DAO<Financement> dao = DAOFactory.getDAO(DAONames.Financement);
            this.instance = dao.findById(this.id);
        }
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
        this.getInstance();
        this.instance.setId(this.id);
    }

    @Override
    public String getLibelle() {
        this.getInstance();
        return this.instance.getLibelle();
    }

    @Override
    public void setLibelle(String libelle) {
        this.getInstance();
        this.instance.setLibelle(libelle);
    }
}
