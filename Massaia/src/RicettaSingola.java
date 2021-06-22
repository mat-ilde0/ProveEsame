
import java.util.HashMap;

public class RicettaSingola {

	private TipoRicetta tipo;
	private String nome;
	private String descrizione;
	HashMap<Ingrediente, Double> ingredientiRichiesti;    //per ogni ingrediente richiede la quantità in grammi
	
	public RicettaSingola(String tipo, String nome, String descrizione) {
		this.tipo = TipoRicetta.valueOf(tipo);	
		this.nome = nome;
		this.descrizione = descrizione;
		ingredientiRichiesti = new HashMap<Ingrediente, Double>();
	}
	
	/**
	 * Metodo che calcola le calorie totali di una ricetta
	 * @return le calorie totali per questa ricetta
	 */
	public double calorieRicetta(){
		double calorie = 0;
		
		for (Ingrediente ingr : ingredientiRichiesti.keySet()) {
			calorie += ingr.calcolaCalEffettive(ingredientiRichiesti.get(ingr));
		}
		
		return calorie;
	}
	
	/**
	 * Override di toString()
	 */
	@Override
	public String toString() {
		StringBuffer info = new StringBuffer();
		info.append(nome.toUpperCase() + ", " + tipo.toString() + "\n" + descrizione + 
				"\nIngredienti\n " + ingredientiRichiesti.entrySet() + "\n\nCalorie: " + );
		return info.toString();
	}
	
	
	
}
