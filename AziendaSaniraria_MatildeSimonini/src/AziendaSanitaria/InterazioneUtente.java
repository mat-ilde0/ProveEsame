package AziendaSanitaria;

import java.util.ArrayList;

import it.unibs.fp.mylib.InputDati;
import it.unibs.fp.mylib.MyMenu;

public class InterazioneUtente {

	public static final String [] vociMenu = { "Aggiunta medico", "Aggiunta paziente", "Ricerca medico (codice)", "Medici in servizio (giorno)", 
			"Ricerca paziente", "Riassegnamento paziente"};
	
	public static void selezionaOperazione(AziendaSanitaria azienda, int scelta) {
		//MyMenu menuOpzioni = new MyMenu(azienda.getNomeAzienda(), vociMenu);
		
		//int scelta = menuOpzioni.scegli();
		
		switch (scelta) {
		case 0:  //esci
			System.out.println("Grazie per aver usato il nostro servizio, Arrivederci!");
			break;

		case 1:  //aggiunta medico
			String nome = InputDati.leggiStringaNonVuota("Nome del medico: ");
			String cognome = InputDati.leggiStringaNonVuota("Cognome del medico: ");
			String codiceMedico = InputDati.leggiStringaNonVuota("Codice del medico: ");
			String giornoRiposo = InputDati.leggiGiorni("Giorno di riposo: ");
			
			azienda.aggiungiMedico(new Medico(nome, cognome, codiceMedico, giornoRiposo));
			
			break;
		
		case 2:  //aggiunta paziente
			
			if(!azienda.getListaMedici().isEmpty()) {
				String nomePaziente = InputDati.leggiStringaNonVuota("Nome del paziene: ");
				String cognomePaziente = InputDati.leggiStringaNonVuota("Cognome del paziente: ");
				String numeroTesserqa = InputDati.leggiStringaNonVuota("Numero tessera sanitaria: ");
				
				int indiceMedico = azienda.cercaMedicoNominativi(InputDati.leggiStringaNonVuota("Nome medico: "), 
						InputDati.leggiStringaNonVuota("Cognome medico: "));
				
				if(indiceMedico != -1) {
					if(azienda.getListaMedici().get(indiceMedico).trovaPazienteConCodice(numeroTesserqa) == null) {
						Paziente nuovoPaziente = new Paziente(nomePaziente, cognomePaziente, numeroTesserqa);
						nuovoPaziente.setMedicoDiRiferimento(azienda.getListaMedici().get(indiceMedico));
						azienda.getListaMedici().get(indiceMedico).getListaPazienti().add(nuovoPaziente);
					}else System.out.println("Il paziente è già presente sotto questo medico!");
					
				}else System.out.println("Il medico indicato non è presente!");
			
			}else System.out.println("Attualmente non sono presenti medici!!");
			
			break;
		
		case 3:  //ricerca medico
			String codiceMedico1 = InputDati.leggiStringa("Inserire il codice del medico: ");
			
			try {
				String infoMedico = azienda.cercaMedicoCodice(codiceMedico1);
				System.out.println(infoMedico);
			} catch (Exception e) {
				//stampa che il medico non è presente
				System.out.println(e.getMessage());
			}
			
			break;
		
		case 4:  //medici in servizio
			String stringaGiornoRiposo = InputDati.leggiGiorni("Indica il giorno: ");
			ArrayList<Medico> mediciInServizio = azienda.cercaMedicoGiorno(stringaGiornoRiposo);
			
			if(mediciInServizio.isEmpty()) {
				//nel caso in cui non ci siano medici in servizio
				System.out.println(String.format("Il %s non sono presenti medici in servizio.", stringaGiornoRiposo));
			}else {
				for(Medico medico : mediciInServizio) {
					//se invece è presente qualche medico in servizio ne stampa le informazioni
					System.out.println(medico.toString());
				}
			}
			
			break;
		
		case 5:  //ricerca paziente
			String numeroTesseraPaziente = InputDati.leggiStringaNonVuota("Inserise il numero della tessera: ");
			
			if(azienda.cercaPazienteCodice(numeroTesseraPaziente).isBlank()) {
				System.out.println("Il paziente non è presente!");
			}else {
				System.out.println(azienda.cercaPazienteCodice(numeroTesseraPaziente));
			}
			
			break;
		
		case 6:  //riassegnamento paziente
			String numeroTessera = InputDati.leggiStringaNonVuota("Inserire il numero di tessera del paziente da spostare: ");
			String nomeNuovoMedico = InputDati.leggiStringaNonVuota("Inserire il nome del nuovo medico: ");
			String cognomeNuovoMedico = InputDati.leggiStringaNonVuota("Inserire il cognome del nuovo medico: ");
			
			int indiceMedico1 = azienda.cercaMedicoNominativi(nomeNuovoMedico, cognomeNuovoMedico);
			
			if(indiceMedico1 != -1) {
				
				try {
					azienda.riassegnamentoPaziente(azienda.getListaMedici().get(indiceMedico1), numeroTessera);
				} catch (Exception e) {   //nel caso in cui il paziente non esista
					e.getMessage();
				}
				
			}else System.out.println("Il medico presso il quale spostare il paziente non è presente nell'elenco!");
			
			break;
		}
		
	}
	
}
