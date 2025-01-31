package it.polito.tdp.librettovoti.model;

import java.util.*;

public class Libretto {
	
	private List <Voto> voti;

	public Libretto() {
		voti = new ArrayList <Voto> ();
	}
	
	public boolean add(Voto voto) {
// il metodo fa lo stesso lavoro della List, però in questo caso si delega all'oggetto meno intelligente
		if(!isDuplicato(voto) & !isConflitto(voto)) {
			this.voti.add(voto);
			return true;
		} else
			return false;
	}
	
	public Libretto filtraPunti(int punteggio) {
		Libretto res = new Libretto();
		for(Voto v : this.voti)
			if(v.getPunti() == punteggio)
				res.add(v);
		
		return res;
	}
	
	/**
	 * Restituisce il punteggio ottenuto all'esame di cui viene passato come parametro il nome.
	 * @param nomeEsame nome dell'esame
	 * @return punteggio nomerico
	 * @throws IllegalArgumentException se l'esame non viene trovato.
	 */
	public Integer punteggioEsame (String nomeEsame) throws IllegalArgumentException {
		for(Voto v : this.voti)
			if(v.getNomeCorso().compareTo(nomeEsame)==0)
				return v.getPunti();
	// se non c'è il voto bisogna avere un'eccezione che blocchi il programma.
	// per ritornare un oggetto 'null' il valore di ritorno deve essere un wrapped - Integer in questo caso
	//	return null;
		
		throw new IllegalArgumentException("Corso non trovato");
		/* in questo modo si ha anche un messaggio di errore più esplicativo. */
	}
	
	public boolean isDuplicato (Voto v) {
		for(Voto v1 : this.voti) 
			if (v1.equals(v))
				return true;
		
		return false;
	}
	
	public boolean isConflitto (Voto v) {
		Integer punti = this.punteggioEsame(v.getNomeCorso());
		
		if(punti!=null && punti!= v.getPunti())
			return true;
		
		return false;
	}
	
	public List<Voto> getVoti() {
		return this.voti;
	}
	
	public Libretto votiMigliorati() {
		Libretto nuovo = new Libretto();
		for(Voto v : this.voti) {
			int p = v.getPunti();
			if(p>=24)
				p=p+2;
			else
				p++;
			if(p>30)
				p = 30;
			nuovo.add(new Voto(v.getNomeCorso(), p));
		}
		return nuovo;
	}
	
	/**
	 * Cancella i voti inferiori al parametro passato
	 * @param votoSoglia
	 */
	public void cancellaVotiMinori(int punti) {
		for(Voto v : this.voti) {
			if(v.getPunti()<punti)
				this.voti.remove(v);
		}
	}
	
	@Override
	public String toString() {
		return this.voti.toString();
	}
	
}
