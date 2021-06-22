package JunitTests;

import static org.junit.Assert.*;

import org.junit.Test;

import AziendaSanitaria.AziendaSanitaria;
import AziendaSanitaria.Medico;
import AziendaSanitaria.Paziente;

public class MedicoTest {

	@Test
	public void testTrovaPazienteConCodice() {
		Medico medico = new Medico("Bruno", "barbieri", "34512", "MARTEDI");
		
		AziendaSanitaria azienda = new AziendaSanitaria("selù", "nirvaadasdsc");
		azienda.aggiungiMedico(medico);
		
		Paziente paz1 = new Paziente("noemi", "nino", "1235");
		Paziente paz2 = new Paziente("noemissss", "ino", "1200");
		Paziente paz3 = new Paziente("noel", "n", "1233");
		
		azienda.aggiungiPaziente(medico, paz1);
		azienda.aggiungiPaziente(medico, paz2);
		azienda.aggiungiPaziente(medico, paz3);
		
		assertEquals(paz2, medico.trovaPazienteConCodice("1200"));
		
	}

}
