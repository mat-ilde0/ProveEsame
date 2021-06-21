package AziendaSanitaria;

public class Paziente extends Persona{
	
	String numeroTessera;
	Medico medicoDiRiferimento;

	public Paziente(String nome, String cognome, String numeroTessera) {
		super(nome, cognome);
		this.numeroTessera = numeroTessera;
	}
	
	
	
	
	@Override
	public String toString() {
		return  "\nPAZIENTE: " + super.toString() + "\nNumero Tessera: " + numeroTessera;
	}


	
	//GETTERS E SETTERS
	public Medico getMedicoDiRiferimento() {
		return medicoDiRiferimento;
	}


	public void setMedicoDiRiferimento(Medico medicoDiRiferimento) {
		this.medicoDiRiferimento = medicoDiRiferimento;
	}


	public String getNumeroTessera() {
		return numeroTessera;
	}

	
	//HASHCODE E EQUALS
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((medicoDiRiferimento == null) ? 0 : medicoDiRiferimento.hashCode());
		result = prime * result + ((numeroTessera == null) ? 0 : numeroTessera.hashCode());
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
		Paziente other = (Paziente) obj;
		if (medicoDiRiferimento == null) {
			if (other.medicoDiRiferimento != null)
				return false;
		} else if (!medicoDiRiferimento.equals(other.medicoDiRiferimento))
			return false;
		if (numeroTessera == null) {
			if (other.numeroTessera != null)
				return false;
		} else if (!numeroTessera.equals(other.numeroTessera))
			return false;
		return true;
	}
	
	

}
