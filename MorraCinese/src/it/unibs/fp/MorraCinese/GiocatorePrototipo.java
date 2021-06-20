package it.unibs.fp.MorraCinese;

public abstract class GiocatorePrototipo {
	private String nome;
	private Statistiche statisticheDiGioco;
	
	/**
	 * costruttore
	 * @param nome
	 */
	public GiocatorePrototipo(String nome) {
		this.nome = nome;
		this.statisticheDiGioco = new Statistiche();
	}
	
	/**
	 * metodo, proprio del giocatore (se utente o PC) che permette di scegliere una mossa
	 */
	public abstract int scegliMossa();
	
	
	
	@Override
	public String toString() {
		return "Nome = " + nome + "\nstatisticheDiGioco:\n" + statisticheDiGioco.toString();
	}

		//GETTERS E SETTERS
		public String getNome() {
			return nome;
		}
		
		public Statistiche getStatisticheDiGioco() {
			return statisticheDiGioco;
		}

		public void setStatisticheDiGioco(Statistiche statisticheDiGioco) {
			this.statisticheDiGioco = statisticheDiGioco;
		}
	
}
