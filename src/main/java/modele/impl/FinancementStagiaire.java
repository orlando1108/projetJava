package modele.impl;

import modele.exception.ModelException;
import modele.intf.IFinancement;
import modele.intf.IFinancementStagiaire;
import modele.intf.IStagiaire;

import java.time.LocalDate;
/**
 * Created by VTanchereau on 28/06/2017.
 */
public class FinancementStagiaire implements IFinancementStagiaire {

    private IStagiaire stagiaire;
    private IFinancement financement;
    private LocalDate dateDebut;
    private LocalDate dateFin;

    public FinancementStagiaire(IStagiaire stagiaire, IFinancement financement, LocalDate dateDebut, LocalDate dateFin) {
        this.stagiaire = stagiaire;
        this.financement = financement;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
    }

    public FinancementStagiaire(IStagiaire stagiaire, IFinancement financement, LocalDate dateDebut) {
        this.stagiaire = stagiaire;
        this.financement = financement;
        this.dateDebut = dateDebut;
    }

    public IStagiaire getStagiaire() {
        return stagiaire;
    }

    public void setStagiaire(IStagiaire stagiaire) throws ModelException{
    	
    	for(IFinancementStagiaire  financementStg : stagiaire.getListFinancementsStagiaires()){
    		
    		if(!(financementStg.getDateDebut().isBefore(this.dateDebut) || financementStg.getDateDebut().isAfter(dateFin)&&
    			 financementStg.getDateFin().isBefore(this.dateDebut) || financementStg.getDateFin().isAfter(this.dateFin))||
    			 financementStg.getDateDebut().isEqual(this.dateDebut) && financementStg.getDateFin().isEqual(this.dateFin)  ){
        		throw new ModelException("Deux creneaux se chevauchent");
        	}
    		
    	}
    
        this.stagiaire = stagiaire;
    }

    public IFinancement getFinancement() {
        return financement;
    }

    public void setFinancement(IFinancement financement) {
        this.financement = financement;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDate dateDebut) throws ModelException {
    	
    	if(dateDebut.isAfter(this.dateFin) || dateDebut.equals(this.dateFin)){
    		throw new ModelException("date de debut  invalide");
    	}
        this.dateDebut = dateDebut;
        
        
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDate dateFin) throws ModelException {
    	if(dateFin.isBefore(this.dateDebut) || dateFin.equals(this.dateDebut)){
    		throw new ModelException("date de fin invalide");
    	}
    	this.dateFin = dateFin;
    }
}
