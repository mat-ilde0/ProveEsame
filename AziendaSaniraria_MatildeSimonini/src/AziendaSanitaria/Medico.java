package AziendaSanitaria;

import java.util.ArrayList;

public class Medico extends Persona{
	
	private String codiceUnivoco;
	private GiorniDellaSettimana giornoDiRiposo;
	private ArrayList<Paziente> listaPazienti;

	
	public Medico(String nome, String cognome, String codiceUnivoco, String GiornoRiposo){
		super(nome, cognome);
		this.codiceUnivoco = codiceUnivoco;
		this.giornoDiRiposo = GiorniDellaSettimana.valueOf(GiornoRiposo.toUpperCase());
		this.listaPazienti = new ArrayList<Paziente>();
		
	}
	
	
	/**
	 * Metodo che permette di verificare la presenza di un paziente nella lista di un medico, tramite il suo numero di tessera sanitaria
	 * @param numeroTessera
	 * @return un Paziente se è stato trovato, altrimenti ritorna null se non esiste in quel medico
	 */
	public Paziente trovaPazienteConCodice(String numeroTessera) {
		for(Paziente paziente : listaPazienti) {
			if(paziente.getNumeroTessera().equals(numeroTessera)) return paziente;
		}
		
		return null;
	}
	
	@Override
	public String toString() {
		return "\nMEDICO: " + super.toString() + "\nGiorno di Riposo: " + giornoDiRiposo.toString();
	}

	
	//GETTERS E SETTERS
	public String getCodiceUnivoco() {
		return codiceUnivoco;
	}

	public GiorniDellaSettimana getGiornoDiRiposo() {
		return giornoDiRiposo;
	}

	public ArrayList<Paziente> getListaPazienti() {
		return listaPazienti;
	}


	//EQUALS E HASHCODE
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codiceUnivoco == null) ? 0 : codiceUnivoco.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Medico other = (Medico) obj;
		if (codiceUnivoco == null) {
			if (other.codiceUnivoco != null)
				return false;
		} else if (!codiceUnivoco.equals(other.codiceUnivoco))
			return false;
		return true;
	}
	
}
