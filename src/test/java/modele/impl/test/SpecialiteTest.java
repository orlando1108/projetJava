package modele.impl.test;

import static org.junit.Assert.*;

import org.junit.Test;

import modele.exception.ModelException;
import modele.impl.Specialite;

public class SpecialiteTest {

	@Test
	public void testMonCodeATroisChiffres() throws ModelException{
		
		Specialite specialite1 = new Specialite();
		specialite1.setCode("222");	
		assertEquals("222", specialite1.getCode());
	}
	
	@Test(expected = ModelException.class)
	public void testMonCodeAQuatreChiffres() throws ModelException{
		Specialite spe = new Specialite();
		spe.setCode("1234");
		/*Si une exception est levée alors le test à reussi et effectivement le code renseigné ne correspond pas aux attentes*/
	}
	@Test(expected = ModelException.class)
	public void testMonCodeAUnChiffres() throws ModelException{
		Specialite spe = new Specialite();
		spe.setCode("1");
	}
	@Test(expected = ModelException.class)
	public void testMonCodeADeuxChiffres()throws ModelException{
		Specialite spe = new Specialite();
		spe.setCode("12");
	}
	
	@Test(expected = ModelException.class)
	public void testMonCodeATroisLettres()throws ModelException{
		Specialite spe = new Specialite();
		spe.setCode("AAA");
	}
	@Test(expected = ModelException.class)
	public void testMonCodeADeuxLettres()throws ModelException{
		Specialite spe = new Specialite();
		spe.setCode("AA");
	}
	@Test(expected = ModelException.class)
	public void testMonCodeAUneLettre()throws ModelException{
		Specialite spe = new Specialite();
		spe.setCode("A");
	}
	@Test(expected = ModelException.class)
	public void testMonCodeAQuattreLettres()throws ModelException{
		Specialite spe = new Specialite();
		spe.setCode("AAAAA");
	}
	@Test(expected = ModelException.class)
	public void testMonCodeAAuMoinsUneLettre()throws ModelException{
		Specialite spe = new Specialite();
		spe.setCode("2A2");
	}
	@Test(expected = ModelException.class)
	public void testMonCodeEstVide()throws ModelException{
		Specialite spe = new Specialite();
		spe.setCode("");
	}
	
	

}
