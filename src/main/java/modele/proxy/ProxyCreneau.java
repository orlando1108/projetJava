package modele.proxy;

import dao.DAOFactory;
import dao.DAONames;
import dao.intf.DAO;
import modele.impl.Creneau;
import modele.intf.ICreneau;
import modele.intf.IFormateur;
import modele.intf.IFormation;
import modele.intf.IStagiaire;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by VTanchereau on 28/06/2017.
 */
public class ProxyCreneau implements ICreneau {

    private int id;
    private Creneau instance;

    public ProxyCreneau(int id) {
        this.id = id;
        this.instance = null;
    }

    private void getInstance(){
        if (instance == null) {
            DAO<Creneau> dao = DAOFactory.getDAO(DAONames.Creneau);
            this.instance = dao.findById(this.id);
        }
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public void setId(int id) {
        getInstance();
        this.instance.setId(id);
    }

    @Override
    public LocalDateTime getDateDebut() {
        getInstance();
        return this.instance.getDateDebut();
    }

    @Override
    public void setDateDebut(LocalDateTime dateDebut) {
        getInstance();
        this.instance.setDateDebut(dateDebut);
    }

    @Override
    public LocalDateTime getDateFin() {
        getInstance();
        return this.instance.getDateFin();
    }

    @Override
    public void setDateFin(LocalDateTime dateFin) {
        getInstance();
        this.instance.setDateFin(dateFin);
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
    public IFormation getFormation() {
        getInstance();
        return this.instance.getFormation();
    }

    @Override
    public void setFormation(IFormation formation) {
        getInstance();
        this.instance.setFormation(formation);
    }

    @Override
    public List<IStagiaire> getListStagiaires() {
        getInstance();
        return this.instance.getListStagiaires();
    }

    @Override
    public void setListStagiaires(List<IStagiaire> listStagiaires) {
        getInstance();
        this.instance.setListStagiaires(listStagiaires);
    }

    @Override
    public List<IFormateur> getListFormateurs() {
        getInstance();
        return this.instance.getListFormateurs();
    }

    @Override
    public void setListFormateurs(List<IFormateur> listFormateurs) {
        getInstance();
        this.instance.setListFormateurs(listFormateurs);
    }
}
