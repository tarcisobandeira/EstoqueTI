package br.com.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.entities.Entrada;
import br.com.entities.Itens;
import br.com.entities.Localizacao;
import br.com.jdbc.ConnectionDB;

public class entradaDAO {

	Connection con;

	public entradaDAO() {
		con = ConnectionDB.getConnection();
	}

	public boolean inserir(Entrada en) {
		String sql = " INSERT INTO Entrada (id_itens, id_localizacao, entrada, dia, codigo) VALUES (?,?,?,?,?) ";

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, en.getId_itens());
			ps.setInt(2, en.getId_localizacao());
			ps.setInt(3, en.getEntrada());
			ps.setString(4, en.getDia());
			ps.setInt(5, en.getCodigo());

			if (ps.executeUpdate() == 1) {
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public List<Entrada> listarTodos() {
		List<Entrada> list = new ArrayList<Entrada>();
		String sql = 	" SELECT e.*, " + 
						" i.descricao AS nomeItem, " + 
						" l.local_nome AS nomeLocal " + 
						" FROM entrada e " + 
						" INNER JOIN itens i " + 
						" ON e.id_itens = i.id " + 
						" INNER JOIN localizacao l " + 
						" ON e.id_localizacao = l.id " +
						" ORDER BY e.id DESC ";

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Entrada en = new Entrada();
				en.setId(rs.getInt("id"));
				en.setCodigo(rs.getInt("codigo"));
				en.setId_itens(rs.getInt("id_itens"));
				en.setId_localizacao(rs.getInt("id_localizacao"));
				en.setEntrada(rs.getInt("entrada"));
				en.setDia(rs.getString("dia"));
				en.setItens(new Itens(en.getId_itens(), rs.getString("nomeItem"), null, null, null, null));
				en.setLocalizacao(new Localizacao(en.getId_localizacao(), rs.getString("nomeLocal"), null));
				
				list.add(en);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public Entrada buscarEntrada(int id) {
		String sql = 	" SELECT e.*, " + 
						" i.descricao AS nomeItem, " + 
						" l.local_nome AS nomeLocal " + 
						" FROM entrada e " + 
						" INNER JOIN itens i " + 
						" ON e.id_itens = i.id " + 
						" INNER JOIN localizacao l " + 
						" ON e.id_localizacao = l.id " + 
						" WHERE i.id = ? ";
		
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				Entrada en = new Entrada();
				en.setItens(new Itens(en.getId_itens(), rs.getString("nomeItem"), null, null, null, null));
				en.setLocalizacao(new Localizacao(en.getId_localizacao(), rs.getString("nomeLocal"), null));
				
				return en;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Entrada> buscarCodigo(int codigo) {
		List<Entrada> list = new ArrayList<Entrada>();
		String sql = 	" SELECT id_itens FROM entrada WHERE codigo = ? ";

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, codigo);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Entrada en = new Entrada();
				en.setId_itens(rs.getInt("id_itens"));
				
				list.add(en);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
}
