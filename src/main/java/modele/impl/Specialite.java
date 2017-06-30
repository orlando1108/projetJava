package modele.impl;

import modele.exception.ModelException;
import modele.intf.IFormation;
import modele.intf.ISpecialite;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by VTanchereau on 27/06/2017.
 */
public class Specialite implements ISpecialite {
    private int id;
    private String nom;
    private String code;
    private List<IFormation> listFormations;

    public Specialite(int id, String nom, String code) {
        this.id = id;
        this.nom = nom;
        this.code = code;
        this.listFormations = new ArrayList<>();
    }

    public Specialite(String nom, String code) {
        this.nom = nom;
        this.code = code;
        this.listFormations = new ArrayList<>();
    }

    public Specialite() {}

	public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) throws ModelException{
        
        int longueur = code.length();
        
        if(longueur == 3 ){
        	for(int i = 0; i<longueur ; i ++){
        		if (Character.digit(code.charAt(i),10) == -1){
        			throw new ModelException("Code invalide, contient des caractères non numérique");
        		}
        	}
        	
        }
        else{
        	throw new ModelException("code n'ayant pas 3 caractères");
        }
        
        this.code = code;
    }

    public List<IFormation> getListFormations() {
        return listFormations;
    }

    public void setListFormations(List<IFormation> listFormations) {
        this.listFormations = listFormations;
    }

    @Override
    public String toString() {
        return "Specialite{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
