package it.unibs.fp.MorraCinese;

public class Statistiche {
	
	private int partiteGiocate;
	private int partiteVinte;
	private int pareggi;
	
	//costruttore vuoto
	public Statistiche() {
		this.partiteVinte = 0;
		this.partiteGiocate = 0;
		this.pareggi = 0;
	}
	
	@Override
	public String toString() {
		return "- Partite vinte = " + partiteVinte + "/" + partiteGiocate + "\n" + "- Partite pareggiate = " + pareggi + "/" + partiteGiocate;
	}

	//GETTERS E SETTERS
	public int getPartiteGiocate() {
		return partiteGiocate;
	}

	public void incrementaPartiteGiocate() {
		this.partiteGiocate++;
	}

	public int getPartiteVinte() {
		return partiteVinte;
	}

	public void incrementaPartiteVinte() {
		this.partiteVinte++;
	}

	public int getPareggi() {
		return pareggi;
	}

	public void incrementaPareggi() {
		this.pareggi++;
	}

	public void setPartiteVinte(int partiteVinte) {
		this.partiteVinte = partiteVinte;
	}
	
	
	
	
	
}
