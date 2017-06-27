package modele;

import dao.ObjectifDAO;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by VTanchereau on 27/06/2017.
 */
public class Objectif {

    private int id;
    private String libelle;

    public Objectif(String libelle) {
        this.libelle = libelle;
    }

    public Objectif(int id, String libelle) {
        this.id = id;
        this.libelle = libelle;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public List<String> getFields(){
        List<String> listFields = new ArrayList<String>();
        for (Field f : getClass().getDeclaredFields()) {
            listFields.add(f.getName());
        }
        return listFields;
    }
}
