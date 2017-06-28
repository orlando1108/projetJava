package modele.proxy;

import dao.DAOFactory;
import dao.DAONames;
import dao.intf.DAO;
import modele.impl.Stagiaire;
import modele.intf.ICreneau;
import modele.intf.IFinancementStagiaire;
import modele.intf.IStagiaire;

import java.util.List;

/**
 * Created by VTanchereau on 28/06/2017.
 */
public class ProxyStagiaire implements IStagiaire {

    private int id;
    private Stagiaire instance;

    public ProxyStagiaire(int id) {
        this.id = id;
        this.instance = null;
    }

    private void getInstance(){
        if (instance == null) {
            DAO<Stagiaire> dao = DAOFactory.getDAO(DAONames.Stagiaire);
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
        return this.getNom();
    }

    @Override
    public void setNom(String nom) {
        this.getInstance();
        this.instance.setNom(nom);
    }

    @Override
    public String getPrenom() {
        this.getInstance();
        return this.getPrenom();
    }

    @Override
    public void setPrenom(String prenom) {
        this.getInstance();
        this.instance.setPrenom(prenom);
    }

    @Override
    public boolean isInterne() {
        this.getInstance();
        return this.isInterne();
    }

    @Override
    public void setInterne(boolean interne) {
        this.getInstance();
        this.instance.setInterne(interne);
    }

    @Override
    public List<IFinancementStagiaire> getListFinancementsStagiaires() {
        this.getInstance();
        return this.instance.getListFinancementsStagiaires();
    }

    @Override
    public void setListFinancementsStagiaires(List<IFinancementStagiaire> listFinancementsStagiaires) {
        this.getInstance();
        this.instance.setListFinancementsStagiaires(listFinancementsStagiaires);
    }

    @Override
    public List<ICreneau> getListCreneaux() {
        this.getInstance();
        return this.instance.getListCreneaux();
    }

    @Override
    public void setListCreneaux(List<ICreneau> listCreneaux) {
        this.getInstance();
        this.instance.setListCreneaux(listCreneaux);
    }
}
