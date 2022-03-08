package it.polito.tdp.librettovoti.model;

public class Voto {
	
	private String nomeCorso;
	private int punteggio;
	
	public Voto(String nomeCorso, int punti) {
		this.nomeCorso = nomeCorso;
		this.punteggio = punti;
	}

	public String getNomeCorso() {
		return nomeCorso;
	}

	public void setNomeCorso(String nomeCorso) {
		this.nomeCorso = nomeCorso;
	}

	public int getPunti() {
		return punteggio;
	}

	public void setPunti(int punti) {
		this.punteggio = punti;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nomeCorso == null) ? 0 : nomeCorso.hashCode());
		result = prime * result + punteggio;
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
		Voto other = (Voto) obj;
		if (nomeCorso == null) {
			if (other.nomeCorso != null)
				return false;
		} else if (!nomeCorso.equals(other.nomeCorso))
			return false;
		if (punteggio != other.punteggio)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return  nomeCorso + ": " + punteggio;
	}
	
	
	
}
