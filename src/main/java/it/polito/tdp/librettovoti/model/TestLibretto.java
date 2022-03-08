package it.polito.tdp.librettovoti.model;

public class TestLibretto {

	public static void main(String[] args) {
		
		Libretto lib = new Libretto();
		
		lib.add(new Voto("Analisi 1", 30));
		lib.add(new Voto("Fisica 1", 18));
		
		/* System.out.println(lib); */
		
//---------------------------------------------------------------------------------------------------
		// creo un nuovo Oggetto - Libretto, che contiene solo i voti da 'parametro' 
		// --> guadagno in flessibilità e soprattutto il fatto che l'oggetto sa già stamparsi da solo
		
		lib.add(new Voto("Informatica", 25));
		lib.add(new Voto("LAG", 25));
		
		System.out.println("Voti pari a 25:");
		Libretto lib25 = lib.filtraPunti(25);
		
		System.out.println(lib25);
		
	}

}
