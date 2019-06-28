package br.com.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.entities.Localizacao;
import br.com.jdbc.ConnectionDB;

public class localizacaoDAO {

	Connection con;

	public localizacaoDAO() {
		con = ConnectionDB.getConnection();
	}

	public boolean inserir(Localizacao l) {
		String sql = "INSERT INTO Localizacao (local_nome, localNF) VALUES (?,?)";

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, l.getLocal_nome());
			ps.setInt(2, l.getLocalNF());

			if (ps.executeUpdate() == 1) {
				return true;
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;

	}

	public List<Localizacao> listarTodosN() {
		List<Localizacao> list = new ArrayList<Localizacao>();
		String sql = " SELECT * FROM Localizacao WHERE localNF = 1 ";

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Localizacao l = new Localizacao();
				l.setId(rs.getInt("id"));
				l.setLocal_nome(rs.getString("local_nome"));
				list.add(l);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	public List<Localizacao> lDisponivel(int id) {
		List<Localizacao> list = new ArrayList<Localizacao>();
		String sql = " SELECT * FROM Localizacao WHERE localNF = 1 AND id != ? ";

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Localizacao l = new Localizacao();
				l.setId(rs.getInt("id"));
				l.setLocal_nome(rs.getString("local_nome"));
				list.add(l);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public List<Localizacao> listarTodosF() {
		List<Localizacao> list = new ArrayList<Localizacao>();
		String sql = " SELECT * FROM Localizacao WHERE localNF = 2 ";

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Localizacao l = new Localizacao();
				l.setId(rs.getInt("id"));
				l.setLocal_nome(rs.getString("local_nome"));
				list.add(l);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public Localizacao buscarLocal(int id) {
		String sql = "SELECT * FROM Localizacao WHERE id = ?";

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				Localizacao l = new Localizacao();
				l.setId(rs.getInt("id"));
				l.setLocal_nome(rs.getString("local_nome"));

				return l;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean buscarLocalNome(String nome) {
		String sql = "SELECT * FROM Localizacao WHERE local_nome = ?";

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, nome);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
}
