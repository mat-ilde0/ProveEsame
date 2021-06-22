package AziendaSanitaria;

import it.unibs.fp.mylib.InputDati;
import it.unibs.fp.mylib.MyMenu;

public class MainClass {

	private static final String DESCRIZIONE = "E' richiesta una breve descrizione dell'azienda: ";
	private static final String NOME_AZIENDA_SANITARIA = "Inserire il nome dell'azienda sanitaria: ";

	public static void main(String[] args) {
		
		String nome = InputDati.leggiStringaNonVuota(NOME_AZIENDA_SANITARIA);
		String descrizione = InputDati.leggiStringaNonVuota(DESCRIZIONE);
		
		AziendaSanitaria nuovaAzienda = new AziendaSanitaria(nome, descrizione);
		
		int comando;
		
		do {
			MyMenu menuOpzioni = new MyMenu(nuovaAzienda.getNomeAzienda(), InterazioneUtente.vociMenu);
			comando = menuOpzioni.scegli();
			
			InterazioneUtente.selezionaOperazione(nuovaAzienda, comando);
			
		}while(comando != 0);

	}

}
