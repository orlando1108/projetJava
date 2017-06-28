package modele.proxy;

import dao.DAOFactory;
import dao.DAONames;
import dao.intf.DAO;
import modele.impl.Formation;
import modele.intf.IFormation;
import modele.intf.IObjectif;
import modele.intf.ISpecialite;

/**
 * Created by VTanchereau on 28/06/2017.
 */
public class ProxyFormation implements IFormation{
    private int id;
    private Formation instance;

    public ProxyFormation(int id) {
        this.id = id;
        this.instance = null;
    }

    private void getInstance(){
        if (instance == null) {
            DAO<Formation> dao = DAOFactory.getDAO(DAONames.Formation);
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
        getInstance();

        this.instance.setId(id);
    }

    @Override
    public String getNom() {
        getInstance();
        return this.instance.getNom();
    }

    @Override
    public void setNom(String nom) {
        getInstance();
        this.instance.setNom(nom);
    }

    @Override
    public ISpecialite getSpecialite() {
        getInstance();
        return this.instance.getSpecialite();
    }

    @Override
    public void setSpecialite(ISpecialite specialite) {
        getInstance();
        this.instance.setSpecialite(specialite);
    }

    @Override
    public IObjectif getObjectif() {
        getInstance();
        return this.instance.getObjectif();
    }

    @Override
    public void setObjectif(IObjectif objectif) {
        getInstance();
        this.instance.setObjectif(objectif);

    }
}
