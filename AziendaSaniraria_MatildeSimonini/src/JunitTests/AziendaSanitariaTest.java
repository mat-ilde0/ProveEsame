package JunitTests;

import static org.junit.Assert.*;

import org.junit.Test;

import AziendaSanitaria.AziendaSanitaria;
import AziendaSanitaria.Medico;
import AziendaSanitaria.Paziente;

public class AziendaSanitariaTest {

	@Test
	public void testAggiungiMedico() {
		AziendaSanitaria azienda = new AziendaSanitaria("Azienda", "descrizione brutta ma bella");
		azienda.aggiungiMedico(new Medico("Antonio", "Nicola", "1234", "LUNEDI"));
		
		assertTrue(!azienda.getListaMedici().isEmpty());
	}

	@Test
	public void testAggiungiPaziente() {
		AziendaSanitaria azienda = new AziendaSanitaria("Azienda", "descrizione brutta ma bella");
		Medico medico = new Medico("Antonio", "Nicola", "1234", "LUNEDI");
		azienda.aggiungiMedico(medico);
		azienda.aggiungiPaziente(medico, new Paziente("Luca", "Matisse", "12345678"));
		
		assertTrue(!azienda.getListaMedici().get(0).getListaPazienti().isEmpty());
		
	}

	@Test
	public void testCercaMedicoCodice() {
		AziendaSanitaria azienda = new AziendaSanitaria("Azienda", "descrizione brutta ma bella");
		azienda.aggiungiMedico(new Medico("Antonio", "Nicola", "1234", "LUNEDI"));
		String info = azienda.cercaMedicoCodice("1234");
		
		System.out.println(info);
		
		assertFalse(info.isBlank());
	}

	@Test
	public void testCercaMedicoGiorno() {
		Medico medico1 = new Medico("G", "N", "kmlkaoi20", "LUNEDI");
		Medico medico2 = new Medico("P", "S", "kmlkaoi20", "MARTEDI");
		Medico medico3 = new Medico("G", "MM", "kmlkaoi20", "SABATO");
		Medico medico4 = new Medico("G", "X", "kmlkaoi20", "SABATO");
		
		AziendaSanitaria azienda = new AziendaSanitaria("Azienda", "descrizione brutta ma bella");
		azienda.aggiungiMedico(medico1);
		azienda.aggiungiMedico(medico2);
		azienda.aggiungiMedico(medico3);
		azienda.aggiungiMedico(medico4);
		
		assertTrue(azienda.cercaMedicoGiorno("LUNEDI").size() == 3);
		
				
	}

	@Test
	public void testCercaMedicoNominativi() {
		Medico medico1 = new Medico("G", "N", "kmlkaoi20", "LUNEDI");
		Medico medico2 = new Medico("P", "S", "kmlkaoi20", "MARTEDI");
		AziendaSanitaria azienda = new AziendaSanitaria("Azienda", "descrizione brutta ma bella");
		azienda.aggiungiMedico(medico1);
		azienda.aggiungiMedico(medico2);
		
		assertEquals(medico2, azienda.getListaMedici().get(azienda.cercaMedicoNominativi("P", "s")));
	}

	@Test
	public void testCercaPazienteCodice() {
		Medico medico1 = new Medico("G", "N", "kmlkaoi20", "LUNEDI");
		Medico medico2 = new Medico("P", "S", "kmlkaoi20", "MARTEDI");
		AziendaSanitaria azienda = new AziendaSanitaria("Azienda", "descrizione brutta ma bella");
	
		azienda.aggiungiMedico(medico1);
		azienda.aggiungiMedico(medico2);
		azienda.aggiungiPaziente(medico2, new Paziente("ces", "cassiopeo", "123457"));
		azienda.aggiungiPaziente(medico2, new Paziente("MM", "III", "128000"));
		azienda.aggiungiPaziente(medico1, new Paziente("cs", "ceo", "0003457"));
		
		
		System.out.println(azienda.cercaPazienteCodice("123457"));
		
		assertNotEquals("", azienda.cercaPazienteCodice("123457"));

	}

	@Test
	public void testRiassegnamentoPaziente() {
		Medico medico1 = new Medico("G", "N", "kmlkaoi20", "LUNEDI");
		Medico medico2 = new Medico("P", "S", "kmlkaoi20", "MARTEDI");
		AziendaSanitaria azienda = new AziendaSanitaria("Azienda", "descrizione brutta ma bella");
		
		medico1.getListaPazienti().add(new Paziente("Giovannini", "mimini", "123457"));
		medico1.getListaPazienti().add(new Paziente("MM", "ST", "123000"));
		medico2.getListaPazienti().add(new Paziente("Giova", "lis", "133"));
		
		azienda.aggiungiMedico(medico1);
		azienda.aggiungiMedico(medico2);
		
		azienda.riassegnamentoPaziente(medico2, "123000");
		
		assertTrue(azienda.getListaMedici().get(0).getListaPazienti().size() == 1 && azienda.getListaMedici().get(1).getListaPazienti().size() == 2);
		
	}

}
