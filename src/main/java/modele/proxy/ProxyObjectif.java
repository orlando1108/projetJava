package modele.proxy;

import dao.intf.DAO;
import dao.DAOFactory;
import dao.DAONames;
import modele.impl.Objectif;
import modele.intf.IFormation;
import modele.intf.IObjectif;

import java.util.List;

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

    private void getInstance(){
        if (instance == null) {
            DAO<Objectif> dao = DAOFactory.getDAO(DAONames.Objectif);
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
        this.instance.setId(id);
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

    @Override
    public List<IFormation> getListFormations() {
        getInstance();
        return this.instance.getListFormations();
    }

    @Override
    public void setListFormations(List<IFormation> listFormations) {
        getInstance();
        this.instance.setListFormations(listFormations);
    }

    public String toString(){
        this.getInstance();
        return this.instance.toString();
    }
}
