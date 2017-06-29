package modele.proxy;

import dao.DAOFactory;
import dao.DAONames;
import dao.intf.DAO;
import modele.impl.Creneau;
import modele.impl.Formateur;
import modele.intf.ICreneau;
import modele.intf.IFormateur;

import java.util.List;

/**
 * Created by VTanchereau on 28/06/2017.
 */
public class ProxyFormateur implements IFormateur {

    private int id;
    private Formateur instance;

    public ProxyFormateur(int id) {
        this.id = id;
        this.instance = null;
    }

    private void getInstance(){
        if (instance == null) {
            DAO<Formateur> dao = DAOFactory.getDAO(DAONames.Formateur);
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
        getInstance();
        return this.getNom();
    }

    @Override
    public void setNom(String nom) {
        getInstance();
        this.instance.setNom(nom);
    }

    @Override
    public String getPrenom() {
        getInstance();
        return this.instance.getPrenom();
    }

    @Override
    public void setPrenom(String prenom) {
        getInstance();
        this.instance.setPrenom(prenom);
    }

    @Override
    public boolean isInterne() {
        getInstance();
        return this.instance.isInterne();
    }

    @Override
    public void setInterne(boolean interne) {
        getInstance();
        this.instance.setInterne(interne);
    }

    @Override
    public List<ICreneau> getListCreneaux() {
        getInstance();
        return this.instance.getListCreneaux();
    }

    @Override
    public void setListCreneaux(List<ICreneau> listCreneaux) {
        getInstance();
        this.instance.setListCreneaux(listCreneaux);
    }

    public String toString(){
        this.getInstance();
        return this.instance.toString();
    }
}
