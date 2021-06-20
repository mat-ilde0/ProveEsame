package it.unibs.fp.MorraCinese;

import java.util.ArrayList;

import it.unibs.fp.mylib.InputDati;

public class InterazioneEsterna {
	/**
	 * metodo che permette al giocatore di scegliere la mossa desiderata
	 * @return intero corripondere alla scelta (indice + 1 nell'enum)
	 */
	private int selezionaMossa(){
		
		stampaMosse();
		return InputDati.leggiIntero("Scegli la tua mossa", 0, Segno.values().length + 1);
		
	}
	
	/**
	 * stampa a video i comandi relativi alle mosse che l'utente può scegliere
	 */
	private void stampaMosse() {
		ArrayList<String> vociMenuStrings = new ArrayList<String>();
		
		for(Segno segno : Segno.values()) {
			vociMenuStrings.add(segno.name());
		}
		
		for(String voce : vociMenuStrings) {
			System.out.println("- 0 : vedi statistiche ed esci dal gioco.");
			System.out.println(String.format("- %d : %s", vociMenuStrings.indexOf(voce) + 1, voce));
		}
	}
	
	/**
	 * metodo che mostra le scelte che son state fatte dal pc e dall'utente
	 * @param sceltaPC
	 * @param sceltaUtente
	 */
	public static void vediScelte(int sceltaPC, int sceltaUtente, Segno [] mosse){
		System.out.println(String.format("- scelta PC : %s\n- la tua scelta : %s", mosse[sceltaPC - 1].toString(), mosse[sceltaUtente-1].toString()));
	}
	
}
