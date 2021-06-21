package AziendaSanitaria;

import it.unibs.fp.mylib.InputDati;
import it.unibs.fp.mylib.MyMenu;

public class MainClass {

	public static void main(String[] args) {
		
		String nome = InputDati.leggiStringaNonVuota("Inserire il nome dell'azienda sanitaria: ");
		String descrizione = InputDati.leggiStringaNonVuota("E' richiesta una breve descrizione dell'azienda: ");
		
		AziendaSanitaria nuovaAzienda = new AziendaSanitaria(nome, descrizione);
		
		int comando;
		
		do {
			MyMenu menuOpzioni = new MyMenu(nuovaAzienda.getNomeAzienda(), InterazioneUtente.vociMenu);
			comando = menuOpzioni.scegli();
			
			InterazioneUtente.selezionaOperazione(nuovaAzienda, comando);
			
		}while(comando != 0);

	}

}
