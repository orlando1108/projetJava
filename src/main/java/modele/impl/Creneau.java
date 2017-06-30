package modele.impl;

import modele.exception.ModelException;
import modele.impl.Formation;
import modele.intf.ICreneau;
import modele.intf.IFormateur;
import modele.intf.IFormation;
import modele.intf.IStagiaire;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by VTanchereau on 28/06/2017.
 */
public class Creneau implements ICreneau {

	private int id;
	private LocalDateTime dateDebut;
	private LocalDateTime dateFin;
	private boolean interne;
	private IFormation formation;
	private List<IStagiaire> listStagiaires;
	private List<IFormateur> listFormateurs;

	public Creneau(int id, LocalDateTime dateDebut, LocalDateTime dateFin, boolean interne, IFormation formation) {
		this.id = id;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.interne = interne;
		this.formation = formation;
		this.listStagiaires = new ArrayList<>();
		this.listFormateurs = new ArrayList<>();
	}

	public Creneau(LocalDateTime dateDebut, LocalDateTime dateFin, boolean interne, IFormation formation) {
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.interne = interne;
		this.formation = formation;
		this.listStagiaires = new ArrayList<>();
		this.listFormateurs = new ArrayList<>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDateTime getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(LocalDateTime dateDebut) throws ModelException {
    	
		if(dateDebut != null){
			if(!(dateDebut.plusHours(3).plusMinutes(30).isEqual(this.dateFin))){
				throw new ModelException("duree  invalide");
			}
		}
		else{
			throw new ModelException("duree  invalide");
		}
		
        this.dateDebut = dateDebut;
    }

	public LocalDateTime getDateFin() {
		return dateFin;
	}

	public void setDateFin(LocalDateTime dateFin) throws ModelException {
		
		if(dateFin != null){
			if(!(dateFin.minusHours(3).minusMinutes(30).isEqual(this.dateDebut))){
				throw new ModelException("duree  invalide");
			}
		}
		else{
			throw new ModelException("duree  invalide");

		}

		this.dateFin = dateFin;
	}

	public boolean isInterne() {
		return interne;
	}

	public void setInterne(boolean interne) {
		this.interne = interne;
	}

	public IFormation getFormation() {
		return formation;
	}

	public void setFormation(IFormation formation) {
		this.formation = formation;
	}

	public List<IStagiaire> getListStagiaires() {
		return listStagiaires;
	}

	public void setListStagiaires(List<IStagiaire> listStagiaires) throws ModelException {
		
		for(IStagiaire stagiaire : listStagiaires){
			for(ICreneau creneau : stagiaire.getListCreneaux()){
				if(!(creneau.getDateDebut().isBefore(this.dateDebut) || creneau.getDateDebut().isAfter(dateFin)&&
						creneau.getDateFin().isBefore(this.dateDebut) || creneau.getDateFin().isAfter(this.dateFin))||
						creneau.getDateDebut().isEqual(this.dateDebut) && creneau.getDateFin().isEqual(this.dateFin)  ){
		        		
					throw new ModelException("Deux creneaux se chevauchent");
		        	}
			}
		}
		
		this.listStagiaires = listStagiaires;
	}

	public List<IFormateur> getListFormateurs() {
		return listFormateurs;
	}

	public void setListFormateurs(List<IFormateur> listFormateurs) throws ModelException {
		
		for(IFormateur formateur : listFormateurs){
			for(ICreneau creneau : formateur.getListCreneaux()){
				if(!(creneau.getDateDebut().isBefore(this.dateDebut) || creneau.getDateDebut().isAfter(dateFin)&&
						creneau.getDateFin().isBefore(this.dateDebut) || creneau.getDateFin().isAfter(this.dateFin))||
						creneau.getDateDebut().isEqual(this.dateDebut) && creneau.getDateFin().isEqual(this.dateFin)  ){
		        		
					throw new ModelException("Deux creneaux se chevauchent");
		        	}
			}
		}
		this.listFormateurs = listFormateurs;
	}

	@Override
	public String toString() {
		return "Creneau{" + "id=" + id + ", dateDebut=" + dateDebut + ", dateFin=" + dateFin + ", formation="
				+ formation + '}';
	}
}
