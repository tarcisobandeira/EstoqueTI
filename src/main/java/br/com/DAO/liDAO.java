package br.com.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.jdbc.ConnectionDB;

public class liDAO {
	
	Connection con;
	
	public liDAO() {
		con = ConnectionDB.getConnection();
	}
	
	public boolean inserir(int id1, int id2) {
		String sql = " INSERT INTO LI (id_itens, id_localizacao) VALUES (?,?) ";
		
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id1);
			ps.setInt(2, id2);
			
			if(ps.executeUpdate() == 1) {
				return true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
}
