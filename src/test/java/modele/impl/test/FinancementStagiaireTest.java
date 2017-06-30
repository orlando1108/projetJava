package modele.impl.test;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;

import modele.exception.ModelException;
import modele.impl.Financement;
import modele.impl.FinancementStagiaire;
import modele.impl.Stagiaire;

public class FinancementStagiaireTest {

	@Test(expected = ModelException.class)
	public void testDateDeFinEstAvantMaDateDebut() throws ModelException{
		
		String dateDebut =  "2017-06-25";
		String dateFin =  "2016-06-02";
		LocalDate localDateDebut = LocalDate.parse(dateDebut);
		LocalDate localDateFin = LocalDate.parse(dateFin);
		
		Stagiaire stagiaire  = new Stagiaire(1,"Martin", "Coline", true);
		Financement financement = new Financement(1,"OPCA");
		
		FinancementStagiaire financementStagiaire = new FinancementStagiaire(stagiaire,financement,localDateDebut,localDateFin);
	    financementStagiaire.setDateFin(localDateFin);
	    financementStagiaire.setDateDebut(localDateDebut);
	    
	}
	
	@Test
	public void testDateDeFinEstApresMaDateDebut() throws ModelException{
		
		String dateDebut =  "2016-06-25";
		String dateFin =  "2017-06-02";
		LocalDate localDateDebut = LocalDate.parse(dateDebut);
		LocalDate localDateFin = LocalDate.parse(dateFin);
		
		Stagiaire stagiaire  = new Stagiaire(1,"Martin", "Coline", true);
		Financement financement = new Financement(1,"OPCA");
		
		FinancementStagiaire financementStagiaire = new FinancementStagiaire(stagiaire,financement,localDateDebut,localDateFin);
		financementStagiaire.setDateFin(localDateFin);
		financementStagiaire.setDateDebut(localDateDebut);
		assertEquals(financementStagiaire.getDateFin(),localDateFin);
	}
	
	@Test
	public void testDateDebutEstAvantMaDateDeFin() throws ModelException{
		
		String dateDebut = "2016-06-02";
		String dateFin =  "2017-06-25" ;
		LocalDate localDateDebut = LocalDate.parse(dateDebut);
		LocalDate localDateFin = LocalDate.parse(dateFin);
		
		Stagiaire stagiaire  = new Stagiaire(1,"Martin", "Coline", true);
		Financement financement = new Financement(1,"OPCA");
		
		FinancementStagiaire financementStagiaire = new FinancementStagiaire(stagiaire,financement,localDateDebut,localDateFin);
	    financementStagiaire.setDateDebut(localDateDebut);
	    financementStagiaire.setDateFin(localDateFin);
	    assertEquals(financementStagiaire.getDateDebut(),localDateDebut);
	}
	
	@Test(expected = ModelException.class)
	public void testDateDebutEstApresMaDateDeFin() throws ModelException{
		
		String dateDebut =  "2017-06-25";
		String dateFin =  "2016-06-02";
		LocalDate localDateDebut = LocalDate.parse(dateDebut);
		LocalDate localDateFin = LocalDate.parse(dateFin);
		
		Stagiaire stagiaire  = new Stagiaire(1,"Martin", "Coline", true);
		Financement financement = new Financement(1,"OPCA");
		
		FinancementStagiaire financementStagiaire = new FinancementStagiaire(stagiaire,financement,localDateDebut,localDateFin);
		financementStagiaire.setDateDebut(localDateDebut);
		financementStagiaire.setDateFin(localDateFin);
	}
	

}
