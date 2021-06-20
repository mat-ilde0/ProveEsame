package it.unibs.fp.MorraCinese;

import static org.junit.Assert.*;

import org.junit.Test;

public class GiocoTest {

	@Test
	public void testScontro() {
		Utente io = new Utente("me stessa");
		Computer questo = new Computer();
		
		io.getStatisticheDiGioco().setPartiteVinte(4);
		questo.getStatisticheDiGioco().setPartiteVinte(4);
		
		assertEquals(io.getStatisticheDiGioco().getPartiteVinte(), questo.getStatisticheDiGioco().getPartiteVinte());

	}

	/*@Test
	public void testVincitore() {
		fail("Not yet implemented");
	}

	@Test
	public void testToString() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetUtente() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetComputer() {
		fail("Not yet implemented");
	}*/

}
