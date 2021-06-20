package it.unibs.fp.MorraCinese;

public class MainClass {

	public static void main(String[] args) {		
		Gioco superGioco = new Gioco(Utente.creaGiocatore(), new Computer());
		
		do {
			int comdando = superGioco.scontro();
			if(comdando == 0) break;
			else {
				superGioco.getUtente().getStatisticheDiGioco().incrementaPartiteGiocate();
				superGioco.getComputer().getStatisticheDiGioco().incrementaPartiteGiocate();
			}
		}while(superGioco.getComputer().getStatisticheDiGioco().getPartiteVinte() < Gioco.NUMERO_VITTORIE_PER_VINCERE ||
				superGioco.getUtente().getStatisticheDiGioco().getPartiteVinte() < Gioco.NUMERO_VITTORIE_PER_VINCERE);

		//una volta terminato il gioco viene mostrado a video chi è il vincito
		superGioco.vincitore();
		
		//visualizzazione statistiche
		System.out.println(superGioco.toString());
	
		
		FileDati.scritturaFile(superGioco);
	}
	
}
