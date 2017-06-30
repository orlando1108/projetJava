package modele.impl.test;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.Test;

import modele.exception.ModelException;
import modele.impl.Creneau;
import modele.impl.Financement;
import modele.impl.FinancementStagiaire;
import modele.impl.Formation;
import modele.impl.Objectif;
import modele.impl.Specialite;
import modele.impl.Stagiaire;

public class Creneautest {

	@Test(expected = ModelException.class)
	public void testHeureDeFinEstAvantMonHeureDebut() throws ModelException{
		
		
		String dateFin =  "2017-06-25 10:00";
		String dateDebut =  "2017-06-25 13:30";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		LocalDateTime localDateDebut = LocalDateTime.parse(dateDebut, formatter);
		LocalDateTime localDateFin = LocalDateTime.parse(dateFin, formatter);
		
		
		Specialite specialite1 = new Specialite();
		Objectif objectif = new Objectif ("mon objectif");
		
		Formation formation = new Formation(1,"Ma formation",specialite1,objectif);
		Creneau creneau = new Creneau(1, localDateDebut, localDateFin, true, formation);
		creneau.setDateFin(localDateFin);
	}
	
	@Test
	public void testHeureDeFinEstApresMonHeureDebut() throws ModelException{
		
		String dateDebut =  "2017-06-25 10:00";
		String dateFin =  "2017-06-25 13:00";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		LocalDateTime localDateDebut = LocalDateTime.parse(dateDebut, formatter);
		LocalDateTime localDateFin = LocalDateTime.parse(dateFin, formatter);
		
		
		Specialite specialite1 = new Specialite();
		specialite1.setCode("222");	
		Objectif objectif = new Objectif ("mon objectif");
		
		Formation formation = new Formation(1,"Ma formation",specialite1,objectif);
		Creneau creneau = new Creneau(1, localDateDebut, localDateFin, true, formation);
	    assertEquals(creneau.getDateFin(),localDateFin);
	}
	
	@Test
	public void testHeureDebutEstAvantMonHeureDeFin() throws ModelException{
		String dateDebut =  "2017-06-25 09:00";
		String dateFin =  "2017-06-25 12:00";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		LocalDateTime localDateDebut = LocalDateTime.parse(dateDebut, formatter);
		LocalDateTime localDateFin = LocalDateTime.parse(dateFin, formatter);
		
		
		Specialite specialite1 = new Specialite();
		Objectif objectif = new Objectif ("mon objectif");
		
		Formation formation = new Formation(1,"Ma formation",specialite1,objectif);
		Creneau creneau = new Creneau(1, localDateDebut, localDateFin, true, formation);
//
		 assertEquals(creneau.getDateDebut(),localDateDebut);
	}
	
	@Test(expected = ModelException.class)
	public void testHeureDebutEstApresMonHeureFin() throws ModelException{
		
		String dateDebut =  "2017-06-25 13:30";
		String dateFin =  "2017-06-25 10:00";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		LocalDateTime localDateDebut = LocalDateTime.parse(dateDebut, formatter);
		LocalDateTime localDateFin = LocalDateTime.parse(dateFin, formatter);
		
		
		Specialite specialite1 = new Specialite();
		specialite1.setCode("222");	
		Objectif objectif = new Objectif ("mon objectif");
		
		Formation formation = new Formation(1,"Ma formation",specialite1,objectif);
		Creneau creneau = new Creneau(1, localDateDebut, localDateFin, true, formation);
		creneau.setDateDebut(localDateDebut);
		creneau.setDateFin(localDateFin);
	    //assertEquals(creneau.getDateDebut(),localDateDebut);
	}
	
	@Test
	public void testMonCreneauAUneDureeDeTroisHeuresTrentesMinutes() throws ModelException{

		String dateDebut =  "2017-06-25 10:00";
		String dateFin =  "2017-06-25 13:30";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		LocalDateTime localDateDebut = LocalDateTime.parse(dateDebut, formatter);
		LocalDateTime localDateFin = LocalDateTime.parse(dateFin, formatter);
		Specialite specialite1 = new Specialite();
		Objectif objectif = new Objectif ("mon objectif");
		
		Formation formation = new Formation(1,"Ma formation",specialite1,objectif);
		Creneau creneau = new Creneau(1, localDateDebut, localDateFin, true, formation);
		creneau.setDateDebut(localDateDebut);
		creneau.setDateFin(localDateFin);
		assertTrue(true);
	}
	
	@Test(expected = ModelException.class)
	public void testMonCreneauAUneDureeInferieureATroisHeuresTrentesMinutes() throws ModelException{

		String dateDebut =  "2017-06-25 10:00";
		String dateFin =  "2017-06-25 13:00";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		LocalDateTime localDateDebut = LocalDateTime.parse(dateDebut, formatter);
		LocalDateTime localDateFin = LocalDateTime.parse(dateFin, formatter);
		Specialite specialite1 = new Specialite();
		Objectif objectif = new Objectif ("mon objectif");
		
		Formation formation = new Formation(1,"Ma formation",specialite1,objectif);
		Creneau creneau = new Creneau(1, localDateDebut, localDateFin, true, formation);
		creneau.setDateDebut(localDateDebut);
		creneau.setDateFin(localDateFin);
		
	}
	
	@Test(expected = ModelException.class)
	public void testMonCreneauAUneDureeSuperieureATroisHeuresTrentesMinutes() throws ModelException{

		String dateDebut =  "2017-06-25 10:00";
		String dateFin =  "2017-06-25 14:00";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		LocalDateTime localDateDebut = LocalDateTime.parse(dateDebut, formatter);
		LocalDateTime localDateFin = LocalDateTime.parse(dateFin, formatter);
		Specialite specialite1 = new Specialite();
		Objectif objectif = new Objectif ("mon objectif");
		
		Formation formation = new Formation(1,"Ma formation",specialite1,objectif);
		Creneau creneau = new Creneau(1, localDateDebut, localDateFin, true, formation);
		creneau.setDateDebut(localDateDebut);
		creneau.setDateFin(localDateFin);
		
	}

}
