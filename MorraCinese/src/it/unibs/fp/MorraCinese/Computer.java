package it.unibs.fp.MorraCinese;

import it.unibs.fp.mylib.NumeriCasuali;

public class Computer extends GiocatorePrototipo{

	public Computer() {
		super("Il tuo computer");
	}
	
	/**
	 * metodo che determina la scelta da parte del computer 
	 * @return (indice + 1 ) il segno scelto
	 */
	public int scegliMossa() {
		return NumeriCasuali.estraiIntero(1, Segno.values().length);
	}
	
	

}
