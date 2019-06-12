package br.com.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.entities.Emprestimo;
import br.com.jdbc.ConnectionDB;

public class emprestimoDAO {
	
	Connection con;

	public emprestimoDAO() {
		con = ConnectionDB.getConnection();
	}
	
	public boolean inserir(Emprestimo em) {
		String sql = " INSERT INTO Emprestimos (dia_saida, dia_devol, colaborador, id_itens, quantidade, obs, limite) VALUES (?,?,?,?,?,?,?) ";
		
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, em.getDia_saida());
			ps.setString(2, em.getDia_devol());
			ps.setString(3, em.getColaborador());
			ps.setInt(4, em.getId_itens());
			ps.setInt(5, em.getQuantidade());
			ps.setString(6, em.getOBS());
			ps.setInt(7, em.getLimite());
			
			if(ps.executeUpdate() == 1) {
				return true;
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
}
