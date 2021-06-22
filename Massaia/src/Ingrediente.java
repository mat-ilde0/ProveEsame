
public class Ingrediente {
	
	private String nome;
	private double calorieX100g;
	
	public Ingrediente(String nome, double calorieX100g) {
		this.nome = nome;
		this.calorieX100g = calorieX100g;
	}

	/**
	 * Ovverdide di toString
	 */
	@Override
	public String toString() {
		return "\nIngrediente: [nome =" + nome + ", calorieX100g=" + calorieX100g + "cal per 100g]";
	}
	
	//calcola calorie: si inseriscono i grammi effettivi e calcola le calori per quel tot di grammi
	public double calcolaCalEffettive(double grammiEffettivi){
		
		return (grammiEffettivi * this.calorieX100g) / 100;
	}
	
}
