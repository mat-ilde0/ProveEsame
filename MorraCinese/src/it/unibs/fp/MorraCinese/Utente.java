package it.unibs.fp.MorraCinese;

import it.unibs.fp.mylib.InputDati;

public class Utente extends GiocatorePrototipo{
	
	//costruttore per l'utente
	public Utente(String nome) {
		super(nome);
	}

	public static Utente creaGiocatore(){
		String nome = InputDati.leggiStringa("Inserisci il tuo nome--> ");
		return new Utente(nome);
	}
	
	/**
	 * metodo che permette al giocatore di scegliere la mossa desiderata
	 * @return intero corripondere alla scelta (indice + 1 nell'enum)
	 */
	public int scegliMossa(){
		return InputDati.leggiIntero("\nScegli la tua mossa", 0, Segno.values().length);
	}
	

}
