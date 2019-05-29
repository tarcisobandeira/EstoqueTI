package br.com.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.entities.Unidade;
import br.com.jdbc.ConnectionDB;

public class unidadeDAO {
	
	Connection con;
	
	public unidadeDAO() {
		con = ConnectionDB.getConnection();
	}
	
	public boolean inserir(Unidade u) {
		String sql = " INSERT INTO Unidade (unidade) VALUES (?) ";

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, u.getUnidade());
			
			if (ps.executeUpdate() == 1) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public List<Unidade> listarTodos() {
		List<Unidade> list = new ArrayList<Unidade>();
		String sql = " SELECT * FROM Unidade ";

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Unidade u = new Unidade();
				u.setId(rs.getInt("id"));
				u.setUnidade(rs.getString("unidade"));
				list.add(u);
			}

		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return list;
	}
	
}
