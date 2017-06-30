package modele.impl.test;

import static org.junit.Assert.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import modele.exception.ModelException;
import modele.impl.Creneau;
import modele.impl.Formateur;
import modele.impl.Formation;
import modele.impl.Objectif;
import modele.impl.Specialite;
import modele.intf.ICreneau;
import modele.intf.IFormateur;


public class FormateurTest {

	@Test(expected = ModelException.class)
	public void testUnFormateurAPlusieursCreneauxEnMemeTemps() throws ModelException{
		
		String dateDebut =  "2017-06-25 10:00";
		String dateFin =  "2017-06-25 13:00";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		LocalDateTime localDateDebut = LocalDateTime.parse(dateDebut, formatter);
		LocalDateTime localDateFin = LocalDateTime.parse(dateFin, formatter);
		
		Specialite specialite1 = new Specialite();
		Objectif objectif = new Objectif ("mon objectif");
		
		Formation formation = new Formation(1,"Ma formation",specialite1,objectif);
		
		Formateur formateur = new Formateur(1,"Jung","JeanFrançois",true);
        Formateur formateur2 = new Formateur(2,"Jung","JeanFrançois",true);
        Formateur formateur3 = new Formateur(3,"Jung","JeanFrançois",true);
        
		Creneau creneau = new Creneau(1, localDateDebut, localDateFin, true, formation);
		Creneau creneau2 = new Creneau(3, localDateDebut, localDateDebut, true, formation);
		Creneau creneau3 = new Creneau(4, localDateDebut, localDateDebut, true, formation);
		
		List<ICreneau> maListe2 = new ArrayList<>();
		maListe2.add(creneau2);
		maListe2.add(creneau3);
		
		formateur.setListCreneaux(maListe2);
		formateur.setListCreneaux(maListe2);
		formateur.setListCreneaux(maListe2);
		
		 List<IFormateur> maListe = new ArrayList<>();
	        maListe.add(formateur);
			maListe.add(formateur2);
			maListe.add(formateur3);
			
	     creneau.setListFormateurs(maListe);
	}
	
	@Test
	public void testUnFormateurNAPasPlusieursCreneauxEnMemeTemps() throws ModelException{
        
		
		String dateDebut =  "2017-06-25 10:00";
		String dateFin =  "2017-06-25 13:30";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		LocalDateTime localDateDebut = LocalDateTime.parse(dateDebut, formatter);
		LocalDateTime localDateFin = LocalDateTime.parse(dateFin, formatter);
		
		
		String dateDebut2 =  "2017-06-26 13:30";
		String dateFin2 =  "2017-06-26 17:00";
		
		String dateDebut3 =  "2017-06-27 13:30";
		String dateFin3 =  "2017-06-27 17:00";
		
		LocalDateTime localDateDebut2 = LocalDateTime.parse(dateDebut2, formatter);
		LocalDateTime localDateFin2 = LocalDateTime.parse(dateFin2, formatter);
		LocalDateTime localDateDebut3 = LocalDateTime.parse(dateDebut3, formatter);
		LocalDateTime localDateFin3 = LocalDateTime.parse(dateFin3, formatter);
		
		Specialite specialite1 = new Specialite();
		Objectif objectif = new Objectif ("mon objectif");
		
		Formation formation = new Formation(1,"Ma formation",specialite1,objectif);

		Formateur formateur = new Formateur(1,"Jung","JeanFrançois",true);
        Formateur formateur2 = new Formateur(2,"Jung","JeanFrançois",true);
        Formateur formateur3 = new Formateur(3,"Jung","JeanFrançois",true);
        
		Creneau creneau = new Creneau(1, localDateDebut, localDateFin, true, formation);
		Creneau creneau2 = new Creneau(3, localDateDebut2, localDateFin2, true, formation);
		Creneau creneau3 = new Creneau(4, localDateDebut3, localDateFin3, true, formation);
		
		List<ICreneau> maListe2 = new ArrayList<>();
		maListe2.add(creneau2);
		maListe2.add(creneau3);
		
		formateur.setListCreneaux(maListe2);
		formateur.setListCreneaux(maListe2);
		formateur.setListCreneaux(maListe2);
		
		 List<IFormateur> maListe = new ArrayList<>();
	        maListe.add(formateur);
			maListe.add(formateur2);
			maListe.add(formateur3);
			
	     creneau.setListFormateurs(maListe);
	     assertTrue(true);
	}
	

}
