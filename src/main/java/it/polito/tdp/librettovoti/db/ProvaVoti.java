package it.polito.tdp.librettovoti.db;

import java.sql.*;

public class ProvaVoti {
	
	public void aggiungiVoto(String nome, int punti) {
		// per aggiungerlo al DB dovrà fare tutti i passi di apertura connessione, creazione dello statement ...
		String url = "jdbc:mysql://localhost:3306/libretto?user=root&password=filippo";
		try {
			Connection conn = DriverManager.getConnection(url);
			//Statement st = conn.createStatement();
			String sql = "INSERT INTO voti(nome, punti) VALUES (?, ?)";
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, nome);
			st.setInt(2, punti);
			
			int res = st.executeUpdate(); // va usato executeUpdate che ritorna un int
			
			/* */
			st.close();
			conn.close();
			
			if(res == 1) {
				System.out.println("Dato correttamente inserito!");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public static void main(String[] args) {
		// TODO connessione con DB e connector/driver jdbc (già c'è con MariaDB in teoria, guardare nella libreria del progetto Maven)
		
		/* ------------------------------------------------------------------------------------------------------------------------ */
		ProvaVoti pv = new ProvaVoti();
		pv.aggiungiVoto("Tecniche di programmazione", 30);
		/* ------------------------------------------------------------------------------------------------------------------------ */
		
		// classe di prova per collegamento con DB
		
		/* STRINGA DI CONNESSIONE: */
		String url = "jdbc:mysql://"; // --> questa parte serve per collegarsi con una libreria che comunica con mysql; se qualcuno risponde alla richiesta allora verrà usato
		url += "localhost:3306/"; // --> quale è il server fisico e la porta a cui ci si collega (opzionale per 3306)
		url += "libretto"; // --> quale schema all'interno del database va interrogato
		url += "?user=root&password=filippo"; // --> i campi di user e password sono quelli inseriti
										   // --> per XAMPP va omesso
		
		/* APERTURA DI CONNESSIONE: */
		try {
			Connection conn = DriverManager.getConnection(url);
			
			/* creazione della query/statement */
			Statement st = conn.createStatement(); // la query viene detta 'statement'
												   // NB(!) : anche Statement è una interface
												   // analogia fiume: conn è il fiume e statement è la barca che va avanti e indietro
			
			// createStatement() NON DOVREBBE ESSERE USATO per problemi di sicurezza. Usare prepareStatement()
			String sql = "SELECT * FROM voti";
			
			/* esecuzione della query e gestione del risultato */
			ResultSet res = st.executeQuery(sql);	// ResultSet CONTIENE IL RISULTATO; ora va 'estrapolato'
													// NB(!) : anche ResultSet è una interface
			
			// il ResultSet è un 'cursore' che punta all'oggetto che contiene il risultato della query (comportamento come iteratore)
			// esso punta /* PRIMA */ della prima 'riga' del risultato
			// alla fine, esso punterà /* DOPO */ l'ultima riga.
			
			/* */
			while(res.next()) { // è un boolean che è true fintantoché ci sono elementi dopo e false quando non ce n'è più
				String nomeEsame = res.getString("nome");
				int voto = res.getInt("punti");
				
				System.out.println(nomeEsame + " " + voto);
			}
			
			/* */
			st.close(); // lo statement non serve più --> libera lo spazio
						// NB(!) dopo aver chiuso la connessione non ha senso farlo
			
			
			
			// il DB può gestire un certo numero di connessioni, oltre al fatto che consuma risorse --> CHIUDERE LA CONNESSIONE
			
			/* CHIUSURA DELLA CONNESSIONE */
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
			// Connection è una interface; la vera classe sta all'interno del DB; semplicemente fa il connection e 
			// comunica con lei.
		
		
	}

}
