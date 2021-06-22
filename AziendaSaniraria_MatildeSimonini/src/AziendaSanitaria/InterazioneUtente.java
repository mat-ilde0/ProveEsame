package AziendaSanitaria;

import java.io.IOException;
import java.util.ArrayList;

import it.unibs.fp.mylib.InputDati;
import it.unibs.fp.mylib.MyMenu;

public class InterazioneUtente {

	private static final String NUOVO_MEDICO_NON_PRESENTE = "Il medico presso il quale spostare il paziente non è presente nell'elenco!";
	private static final String COGNOME_NUOVO_MEDICO = "Inserire il cognome del nuovo medico: ";
	private static final String NOME_NUOVO_MEDICO = "Inserire il nome del nuovo medico: ";
	private static final String N_TESSERA_PAZIENTE_DA_SPOSTARE = "Inserire il numero di tessera del paziente da spostare: ";
	private static final String PAZIENTE_NON_PRESENTE = "Il paziente non è presente!";
	private static final String NO_MEDICI_IN_SERVIZIO = "Il %s non sono presenti medici in servizio.";
	private static final String NON_SONO_PRESENTI_MEDICI = "Attualmente non sono presenti medici!!";
	private static final String IL_MEDICO_INDICATO_NON_È_PRESENTE = "Il medico indicato non è presente!";
	private static final String PAZIENTE_GIÀ_PRESENTE = "Il paziente è già presente sotto questo medico!";
	private static final String NUMERO_TESSERA = "Numero tessera sanitaria: ";
	private static final String COGNOME_PAZIENTE = "Cognome del paziente: ";
	private static final String NOME_PAZIENE = "Nome del paziene: ";
	private static final String ARRIVEDERCI = "Grazie per aver usato il nostro servizio, Arrivederci!";
	private static final String INDICA_IL_GIORNO = "Indica il giorno: ";
	private static final String GIORNO_DI_RIPOSO = "Giorno di riposo: ";
	private static final String CODICE_DEL_MEDICO = "Codice del medico: ";
	private static final String COGNOME_DEL_MEDICO = "Cognome del medico: ";
	private static final String NOME_DEL_MEDICO = "Nome del medico: ";
	public static final String [] vociMenu = { "Aggiunta medico", "Aggiunta paziente", "Ricerca medico (codice)", "Medici in servizio (giorno)", 
			"Ricerca paziente", "Riassegnamento paziente"};
	
	
	/**
	 * Metodo che si occupa della gestione dell'interazione con l'utente
	 * @param azienda
	 * @param scelta
	 */
	public static void selezionaOperazione(AziendaSanitaria azienda, int scelta) {
		
		switch (scelta) {
		case 0:  //esci
			System.out.println(ARRIVEDERCI);
			break;

		case 1:  //aggiunta medico
			String nome = InputDati.leggiStringaNonVuota(NOME_DEL_MEDICO);
			String cognome = InputDati.leggiStringaNonVuota(COGNOME_DEL_MEDICO);
			String codiceMedico = InputDati.leggiStringaNonVuota(CODICE_DEL_MEDICO);
			String giornoRiposo = InputDati.leggiGiorni(GIORNO_DI_RIPOSO);
			
			azienda.aggiungiMedico(new Medico(nome, cognome, codiceMedico, giornoRiposo));
			
			break;
		
		case 2:  //aggiunta paziente
			
			if(!azienda.getListaMedici().isEmpty()) {
				String nomePaziente = InputDati.leggiStringaNonVuota(NOME_PAZIENE);
				String cognomePaziente = InputDati.leggiStringaNonVuota(COGNOME_PAZIENTE);
				String numeroTesserqa = InputDati.leggiStringaNonVuota(NUMERO_TESSERA);
				
				int indiceMedico = azienda.cercaMedicoNominativi(InputDati.leggiStringaNonVuota(NOME_DEL_MEDICO), 
						InputDati.leggiStringaNonVuota(COGNOME_DEL_MEDICO));
				
				if(indiceMedico != -1) {
					if(azienda.getListaMedici().get(indiceMedico).trovaPazienteConCodice(numeroTesserqa) == null) {
						Paziente nuovoPaziente = new Paziente(nomePaziente, cognomePaziente, numeroTesserqa);
						nuovoPaziente.setMedicoDiRiferimento(azienda.getListaMedici().get(indiceMedico));
						azienda.getListaMedici().get(indiceMedico).getListaPazienti().add(nuovoPaziente);
					}else System.out.println(PAZIENTE_GIÀ_PRESENTE);
					
				}else System.out.println(IL_MEDICO_INDICATO_NON_È_PRESENTE);
			
			}else System.out.println(NON_SONO_PRESENTI_MEDICI);
			
			break;
		
		case 3:  //ricerca medico
			String codiceMedico1 = InputDati.leggiStringa(CODICE_DEL_MEDICO);
			
			try {
				String infoMedico = azienda.cercaMedicoCodice(codiceMedico1);
				System.out.println(infoMedico);
			} catch (Exception e) {
				//stampa che il medico non è presente
				System.out.println(e.getMessage());
			}
			
			break;
		
		case 4:  //medici in servizio
			String stringaGiornoRiposo = InputDati.leggiGiorni(INDICA_IL_GIORNO);
			ArrayList<Medico> mediciInServizio = azienda.cercaMedicoGiorno(stringaGiornoRiposo);
			
			if(mediciInServizio.isEmpty()) {
				//nel caso in cui non ci siano medici in servizio
				System.out.println(String.format(NO_MEDICI_IN_SERVIZIO, stringaGiornoRiposo));
			}else {
				for(Medico medico : mediciInServizio) {
					//se invece è presente qualche medico in servizio ne stampa le informazioni
					System.out.println(medico.toString());
				}
			}
			
			break;
		
		case 5:  //ricerca paziente
			String numeroTesseraPaziente = InputDati.leggiStringaNonVuota(NUMERO_TESSERA);
			
			if(azienda.cercaPazienteCodice(numeroTesseraPaziente).isBlank()) {
				System.out.println(PAZIENTE_NON_PRESENTE);
			}else {
				System.out.println(azienda.cercaPazienteCodice(numeroTesseraPaziente));
			}
			
			break;
		
		case 6:  //riassegnamento paziente
			String numeroTessera = InputDati.leggiStringaNonVuota(N_TESSERA_PAZIENTE_DA_SPOSTARE);
			String nomeNuovoMedico = InputDati.leggiStringaNonVuota(NOME_NUOVO_MEDICO);
			String cognomeNuovoMedico = InputDati.leggiStringaNonVuota(COGNOME_NUOVO_MEDICO);
			
			int indiceMedico1 = azienda.cercaMedicoNominativi(nomeNuovoMedico, cognomeNuovoMedico);
			
			if(indiceMedico1 != -1) {
				
				try {
					azienda.riassegnamentoPaziente(azienda.getListaMedici().get(indiceMedico1), numeroTessera);
				} catch (Exception e) {   //nel caso in cui il paziente non esista
					e.getMessage();
				}
				
			}else System.out.println(NUOVO_MEDICO_NON_PRESENTE);
			
			break;
		}
		
	}
	
	
}
