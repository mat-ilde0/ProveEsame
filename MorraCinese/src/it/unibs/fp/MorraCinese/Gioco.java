package it.unibs.fp.MorraCinese;

import java.util.ArrayList;
import java.util.Arrays;

import it.unibs.fp.mylib.InputDati;
import it.unibs.fp.mylib.NumeriCasuali;

public class Gioco {

	private Utente utente;
	private Computer computer;
	private Segno[] mosse = Segno.values();
	public static final int NUMERO_VITTORIE_PER_VINCERE = 3;
	
	public Gioco(Utente nuovoGiocatore, Computer pc) {
		this.utente = nuovoGiocatore;
		this.computer = pc;
	}
	
	
	/**
	 * metodo che confronta le scelte che sono state fatte e comunica chi ha vinto il giro
	 * @return 0 se il gioco deve terminare per scelta dell'utente oppure 1 se il gioco può porseguire
	 */
	public int scontro(){
		
		System.out.println("Per assicurarti che non vedro' cio' che scegli, la prima mossa la faccio io...\n");
		int sceltaPC = computer.scegliMossa();
		System.out.println("...scelta fatta\n");
		
		stampaMosse();
		int sceltaUtente = utente.scegliMossa();
		
		//cascata di controlli per vedere la situa
		
		//se hanno scelto la stessa cosa
		if(sceltaUtente == 0) return sceltaUtente;
		else if(sceltaPC == sceltaUtente){
			utente.getStatisticheDiGioco().incrementaPareggi();
			computer.getStatisticheDiGioco().incrementaPareggi();
			System.out.println("Avete pareggiato!!");
		
		}else {
			//se vince l'utente allora
			if(checkMosse(sceltaUtente, sceltaPC)) {
				utente.getStatisticheDiGioco().incrementaPartiteVinte();
				System.out.println("Hai vinto!!");
			}
			else {   //se ha vinto il pc
				computer.getStatisticheDiGioco().incrementaPartiteVinte();
				System.out.println("Questa volta hai perso!!");
			}
		}
		//una volta fatti i controlli stampa le scelte che sono state fatte
		InterazioneEsterna.vediScelte(sceltaPC, sceltaUtente, mosse);
		return 1;
	
	}
	
	
	/**
	 * Controlla chi dei dei due ha vinto, nel caso in cui abbiano fatto scelte diverse
	 * @param mossaPC
	 * @param mossaUtente
	 * @return true se ha vinto l'utente, false se ha vinto il PC
	 */
	private Boolean checkMosse(int mossaUtente, int mossaPC) {
		String segnoUtente = mosse[mossaUtente - 1].toString();
		String segnoPC = mosse[mossaPC - 1].toString();
		
		Boolean seUtenteVince = false;
		
		switch (segnoUtente) {
		case "SASSO":
			if(segnoPC.contentEquals("CARTA")) seUtenteVince = false;
			else seUtenteVince = true;
			
			break;
		
		case "CARTA":
			if(segnoPC.contentEquals("FORBICE")) seUtenteVince = false;
			else seUtenteVince = true;
			
			break;
			
		case "FORBICE":
			if(segnoPC.contentEquals("SASSO")) seUtenteVince = false;
			else seUtenteVince = true;
			
			break;
		}
		
		return seUtenteVince;
	
	}
	
	/**
	 * stampa a video i comandi relativi alle mosse che l'utente può scegliere
	 */
	private void stampaMosse() {
		ArrayList<String> vociMenuStrings = new ArrayList<String>();
		
		for(Segno segno : mosse) {
			vociMenuStrings.add(segno.name());
		}
		
		System.out.println("- 0 : vedi statistiche ed esci dal gioco.");
		
		for(String voce : vociMenuStrings) {
			System.out.println(String.format("- %d : %s", vociMenuStrings.indexOf(voce) + 1, voce));
		}
	}
	
	
	public void vincitore(){
		if(utente.getStatisticheDiGioco().getPartiteVinte() > computer.getStatisticheDiGioco().getPartiteVinte()) {
			System.out.println("\nComplimenti, HAI VINTO!!!");
		}else if(utente.getStatisticheDiGioco().getPartiteVinte() < computer.getStatisticheDiGioco().getPartiteVinte()) {
			System.out.println("\nSpiacente, HAI PERSO!!");
		}else System.out.println("\nLa partita si è conclusa con un PAREGGIO!");
	}
	
	@Override
	public String toString() {
		
		return "\nSTATISTICHE DI GIOCO:\n\n" + utente.toString() + "\n\n" + computer.toString();
	}


	public Utente getUtente() {
		return utente;
	}
	
	public Computer getComputer() {
		return computer;
	}
	
	
	
	
}
