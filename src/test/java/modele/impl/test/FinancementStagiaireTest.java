package modele.impl.test;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import modele.exception.ModelException;
import modele.impl.Financement;
import modele.impl.FinancementStagiaire;
import modele.impl.Stagiaire;
import modele.intf.IFinancementStagiaire;

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
	
	@Test(expected = ModelException.class)
	public void testCreneauxFinancementStagiaireSeChevauchent() throws ModelException{
		
		String dateDebut =  "2017-06-25";
		String dateFin =  "2016-06-02";
		LocalDate localDateDebut = LocalDate.parse(dateDebut);
		LocalDate localDateFin = LocalDate.parse(dateFin);
		
		Stagiaire stagiaire  = new Stagiaire(1,"Martin", "Coline", true);
		
		Financement financement = new Financement(1,"OPCA");
		Financement financement2 = new Financement(2,"OTT");
		
		FinancementStagiaire financementStagiaire = new FinancementStagiaire(stagiaire,financement,localDateDebut,localDateFin);
		FinancementStagiaire financementStagiaire2 = new FinancementStagiaire(stagiaire,financement2,localDateDebut,localDateFin);
		
		List<IFinancementStagiaire> listFinancementsStagiaires = new ArrayList<>();
		listFinancementsStagiaires.add(financementStagiaire);
		listFinancementsStagiaires.add(financementStagiaire2);
		
		stagiaire.setListFinancementsStagiaires(listFinancementsStagiaires);
		
		financementStagiaire.setStagiaire(stagiaire);
		//financementStagiaire2.setStagiaire(stagiaire);
	}
	
	@Test
	public void testCreneauxFinancementStagiairesNeSeChevauchentPas() throws ModelException{
		String dateDebut =  "2017-01-20";
		String dateFin =  "2017-06-25";
		LocalDate localDateDebut = LocalDate.parse(dateDebut);
		LocalDate localDateFin = LocalDate.parse(dateFin);
		
		Stagiaire stagiaire  = new Stagiaire(1,"Martin", "Coline", true);
		Financement financement = new Financement(1,"OPCA");
		FinancementStagiaire financementStagiaire = new FinancementStagiaire(stagiaire,financement,localDateDebut,localDateFin);

		
		String dateDebut2 =  "2017-08-25";
		String dateFin2 =  "2018-06-02";
		LocalDate localDateDebut2 = LocalDate.parse(dateDebut2);
		LocalDate localDateFin2 = LocalDate.parse(dateFin2);
		
		Financement financement2 = new Financement(2,"OTT");
		FinancementStagiaire financementStagiaire2 = new FinancementStagiaire(stagiaire,financement2,localDateDebut2,localDateFin2);

		financementStagiaire2.setStagiaire(stagiaire);
		assertTrue(true);
		
		
		
	}
	

}
