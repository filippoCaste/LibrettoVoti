package it.polito.tdp.librettovoti.db;

import java.sql.*;
import java.util.*;

import it.polito.tdp.librettovoti.model.Voto;

public class LibrettoDAO { // Data Access Object
	
	// CRUD = Create Read Update [Delete]
	
	// Serve un Transfer Object che faccia da capsula dati e venga scambiato tra DB e DAO --> in questo caso è l'oggetto Voto
	
	/* C */
	public boolean creaVoto(Voto v) {
		try {
			Connection conn = DBConnect.getConnection();

			String sql = "INSERT INTO voti(nome, punti) VALUES (?, ?)";
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, v.getNome());
			st.setInt(2, v.getPunti());
			
			int res = st.executeUpdate(); // va usato executeUpdate che ritorna un int
			
			/* */
			st.close();
			conn.close();
			
			return (res==1);
			
		} catch(SQLException sqle) {
			System.out.print(sqle);
			sqle.printStackTrace();
			return false;
		}
	}
	
	/* R */
	public List<Voto> readAllVoto() {
		try {
			Connection conn = DBConnect.getConnection();
			
			String sql = "SELECT * FROM voti";
			PreparedStatement st = conn.prepareStatement(sql);
			
			ResultSet res = st.executeQuery();
			List<Voto> result = new ArrayList<> ();
			
			/* */
			while(res.next()) { 
				String nomeEsame = res.getString("nome");
				int voto = res.getInt("punti");
				
				result.add(new Voto(nomeEsame, voto));
			}
			
			/* */
			st.close(); 
			conn.close();
			
			return result;
			
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		}

	}
	
//	public Voto readVotoByName(String nomeEsame) {
//		return null;
//	}
}
