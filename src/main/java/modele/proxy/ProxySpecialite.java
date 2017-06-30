package modele.proxy;

import dao.DAOFactory;
import dao.DAONames;
import dao.intf.DAO;
import modele.exception.ModelException;
import modele.impl.Specialite;
import modele.intf.IFormation;
import modele.intf.ISpecialite;

import java.util.List;

/**
 * Created by VTanchereau on 28/06/2017.
 */
public class ProxySpecialite implements ISpecialite {

    private int id;
    private Specialite instance;

    public ProxySpecialite(int id) {
        this.id = id;
        this.instance = null;
    }

    private void getInstance(){
        if (instance == null) {
            DAO<Specialite> dao = DAOFactory.getDAO(DAONames.Specialite);
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
    public String getNom() {
        this.getInstance();
        return this.instance.getNom();
    }

    @Override
    public void setNom(String nom) {
        this.getInstance();
        this.instance.setNom(nom);
    }

    @Override
    public String getCode() {
        this.getInstance();
        return this.instance.getCode();
    }

    @Override
    public void setCode(String code) throws ModelException{
        this.getInstance();
        this.instance.setCode(code);
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
