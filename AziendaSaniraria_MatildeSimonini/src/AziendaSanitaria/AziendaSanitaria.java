package AziendaSanitaria;

import java.util.ArrayList;

public class AziendaSanitaria {
	
	private String nomeAzienda;
	private String descrizione;
	private ArrayList<Medico> listaMedici;
	
	public AziendaSanitaria(String nomeAzienda, String descrizione) {
		this.nomeAzienda = nomeAzienda;
		this.descrizione = descrizione;
		this.listaMedici = new ArrayList<Medico>();
	}
	
	//MEDOTI DI AGGIUNTA
	
	//ritorna il numero di medici in totale  (nell'interazione esterna ci saranno i vai metodi che prendono anche la gestione con l'utente)
	
	/**
	 * Metodo che consente l'aggiunta di un nuovo medico nell'archivio dell'azienda
	 * @param nuovoMedico il nuovo medico da aggiungere
	 * @return il numero di medici attualmente presenti
	 */
	public int aggiungiMedico(Medico nuovoMedico){
		listaMedici.add(nuovoMedico);
		return listaMedici.size();
	}
	

	/**
	 * Metodo che permette l'aggiunta di un paziente sotto un medico indicato, se non è gia presente.
	 * @param medicoRiferimento
	 * @param nuovoPaziente
	 * @throws IllegalArgumentException se il medico al quale si vuole aggiungere il paziente non esiste
	 * @return il numero di pazienti attualmente presente nella lista del medico, -1 se il paziente era già presente
	 */
	public int aggiungiPaziente(Medico medicoRiferimento, Paziente nuovoPaziente){
		
		if(listaMedici.contains(medicoRiferimento)) {
			if(!medicoRiferimento.getListaPazienti().contains(nuovoPaziente)) {
				nuovoPaziente.setMedicoDiRiferimento(medicoRiferimento);
				medicoRiferimento.getListaPazienti().add(nuovoPaziente);
				return medicoRiferimento.getListaPazienti().size();
			
			}else return -1;
			
		}else throw new IllegalArgumentException("Il medico selezionato non è presente!!");
		
	}
	
	
	//METODI DI RICERCA
	
	//cerca un medico con il suo codice e ritorna il toString del medico altrimenti ritorna stringa vuota oppure eccezione
	/**
	 * Metodo che cerca un medico tramite il suo codice univoco : ritorna la stringa di informazioni se esiste, altrimenti lancia un'eccezione
	 * @param codiceMedico
	 * @return una stringa contenente le informazioni del medico e di tutti i suoi pazienti
	 */
	public String cercaMedicoCodice(String codiceMedico) {
		
		if(listaMedici.isEmpty()) throw new IllegalArgumentException("Attualmente non sono presenti medici nell'azienda!!");
		
		for(Medico medico : listaMedici) {
			if(medico.getCodiceUnivoco().equals(codiceMedico)) {
				String infoMedicoEPazienti = medico.toString();
				
				if(!medico.getListaPazienti().isEmpty())
				for(Paziente paziente : medico.getListaPazienti()) {
					infoMedicoEPazienti = infoMedicoEPazienti.concat(paziente.toString());
				}else infoMedicoEPazienti = infoMedicoEPazienti.concat("\nNon ha pazienti.");
				
				return infoMedicoEPazienti;
			}
		}
	    //se esce dal ciclo senza aver tornato qualcosa vuol dire che il medico non esiste e quindi lancia un'eccezione
		throw new IllegalArgumentException("Il medico selezionato non è presente!!");
	}
	
	
	//cerca un medico con il giorno di riposo e ritorna l'array contente i medici che quel giorno non riposano
	/**
	 * Medoto che una volta inserito il giorno di riposo di un metodo ritorna un ArrayList con i medici che lavorano in quel giorno
	 * (ocio: può succedere che non ci sia nessun medico che lavora in quel giorno, gestiscila nell'interazione)
	 * @param stringaGiorno
	 * @return lista dei medici che in quel giorno non riposano
	 */
	public ArrayList<Medico> cercaMedicoGiorno(String stringaGiorno){
		
		GiorniDellaSettimana giornoRiposo;
		
		try {
			giornoRiposo = GiorniDellaSettimana.valueOf(stringaGiorno.toUpperCase());
		} catch (Exception e) {
			System.out.println("Il giorno inserito non è valido!!");
			return null;
		}

		ArrayList<Medico> mediciCheNonRiposano = new ArrayList<Medico>();
		
		for(Medico medico : listaMedici) {
			if(!medico.getGiornoDiRiposo().equals(giornoRiposo)) mediciCheNonRiposano.add(medico);
		}
		
		return mediciCheNonRiposano;
	}
	
	//ricerca medico tramite nome e cognome
	/*
	 * Metodo che mermette di cercare un medico tramite il suo nome e cognome
	 * @returns int l'indice del medico, -1 se non è presente
	 * @param String nome del medico
	 * @param String cognome del medico
	 */
	public int cercaMedicoNominativi(String nome, String cognome) {
		for(Medico medico : listaMedici) {
			if(medico.getNome().equalsIgnoreCase(nome) && medico.getCognome().equalsIgnoreCase(cognome)) return listaMedici.indexOf(medico);
		}
		return -1;    //se esce dal for vuol dire che ha fatto passare tutti i medici e quello che si vuole trovare non è presente
	}
	
	
	//ricerca paziente, facendo passare tutti i medici, ritorna il tostring del paziente e del medico
	/**
	 * Metodo che consente di trovare un paziente all'interno dell'azienda
	 * @param numeroTessera
	 * @return stringa con info del paziente e del medico di riferimento oppure stringa vuota se il paziente non esiste
	 */
	public String cercaPazienteCodice(String numeroTessera) {
		Paziente pazienteDaCercare = null;
		
		for(Medico medicoRiferimento : listaMedici) {
			pazienteDaCercare = medicoRiferimento.trovaPazienteConCodice(numeroTessera);
			
			if(pazienteDaCercare != null)
				return pazienteDaCercare.toString() + pazienteDaCercare.getMedicoDiRiferimento().toString();
		}
		return "";   //se esce dal ciclo vuol dire che il paziente non esiste
	
	}
	
	/**
	 * Metodo che consente di spostare un paziente (se esiste nell'elenco) da un medico all'altro
	 * @param medicoRif   medico in cui bisogna spostare il paziente
	 * @param daSpostare   paziente da spostare
	 * @throws IllegalArgumentException se il paziente non esiste
	 */
	public void riassegnamentoPaziente(Medico medicoRif, String numeroTesseraDaSpostare) {
		Boolean seRimosso = false;
		Paziente daSpostare = null;
		
		for(Medico medico : listaMedici) {
			daSpostare = medico.trovaPazienteConCodice(numeroTesseraDaSpostare);
			
			if(daSpostare != null && medico.getListaPazienti().contains(daSpostare)) {
				medico.getListaPazienti().remove(daSpostare);
				seRimosso = true;
				break;
			}
		}
		
		if(seRimosso) {
			daSpostare.setMedicoDiRiferimento(medicoRif);
			medicoRif.getListaPazienti().add(daSpostare);
		}else throw new IllegalArgumentException("Il paziente non esiste nell'elenco!!");
	}

	//GETTERS
	public String getNomeAzienda() {
		return nomeAzienda;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public ArrayList<Medico> getListaMedici() {
		return listaMedici;
	}
	
}
