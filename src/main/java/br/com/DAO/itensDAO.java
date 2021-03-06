package br.com.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.entities.Itens;
import br.com.entities.Unidade;
import br.com.jdbc.ConnectionDB;

public class itensDAO {

	Connection con;

	public itensDAO() {
		con = ConnectionDB.getConnection();
	}

	public boolean inserir(Itens i) {
		String sql = "INSERT INTO Itens (descricao, id_unidade, minimo, estoque_at) VALUES (?,?,?,?)";

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, i.getDescricao());
			ps.setInt(2, i.getId_unidade());
			ps.setInt(3, i.getMinimo());
			ps.setInt(4, 0);

			if (ps.executeUpdate() == 1) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public boolean editar(Itens i) {
		String sql = " UPDATE Itens SET descricao = ?, id_unidade = ?, minimo = ? WHERE id = ? ";

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, i.getDescricao());
			ps.setInt(2, i.getId_unidade());
			ps.setInt(3, i.getMinimo());
			ps.setInt(4, i.getId());

			if (ps.executeUpdate() == 1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public Itens buscarItem(int id) {
		String sql = "SELECT * FROM Itens WHERE id = ?";

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				Itens i = new Itens();
				i.setId(rs.getInt("id"));
				i.setDescricao(rs.getString("descricao"));
				i.setId_unidade(rs.getInt("id_unidade"));
				i.setMinimo(rs.getInt("minimo"));
				i.setEstoque_at(rs.getInt("estoque_at"));

				return i;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Itens buscarItemDescricao(String descricao) {
		String sql = "SELECT * FROM Itens WHERE descricao = ?";

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, descricao);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				Itens i = new Itens();
				i.setId(rs.getInt("id"));
				i.setDescricao(rs.getString("descricao"));
				i.setId_unidade(rs.getInt("id_unidade"));
				i.setMinimo(rs.getInt("minimo"));
				i.setEstoque_at(rs.getInt("estoque_at"));

				return i;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Itens> listarTodos() {
		List<Itens> list = new ArrayList<Itens>();
		String sql = " SELECT i.*, u.unidade AS nomeUnidade " 
				+ " FROM itens i " 
				+ " INNER JOIN Unidade u "
				+ " ON i.id_unidade = u.id "
				+ " ORDER BY i.id ASC ";

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Itens i = new Itens();
				i.setId(rs.getInt("id"));
				i.setDescricao(rs.getString("descricao"));
				i.setId_unidade(rs.getInt("id_unidade"));
				i.setUnidade(new Unidade(i.getId_unidade(), rs.getString("nomeUnidade")));
				i.setMinimo(rs.getInt("minimo"));
				i.setEstoque_at(rs.getInt("estoque_at"));
				list.add(i);
			}

		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return list;
	}

	public List<Itens> listarItensFalta() {
		List<Itens> list = new ArrayList<Itens>();
		String sql = " SELECT i.*, u.unidade AS nomeUnidade " 
				+ " FROM itens i " 
				+ " INNER JOIN Unidade u "
				+ "	ON i.id_unidade = u.id	" 
				+ " WHERE i.estoque_at <= i.minimo " 
				+ " ORDER BY i.estoque_at ASC ";

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Itens i = new Itens();
				i.setId(rs.getInt("id"));
				i.setDescricao(rs.getString("descricao"));
				i.setUnidade(new Unidade(i.getId_unidade(), rs.getString("nomeUnidade")));
				i.setMinimo(rs.getInt("minimo"));
				i.setEstoque_at(rs.getInt("estoque_at"));
				list.add(i);
			}

		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return list;
	}

	public boolean updateEstoque(Integer estoque, Integer id) {
		String sql = " UPDATE Itens SET estoque_at = ? WHERE id = ? ";

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, estoque);
			ps.setInt(2, id);

			if (ps.executeUpdate() == 1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public List<Itens> listarTodosOrderDesc() {
		List<Itens> list = new ArrayList<Itens>();
		String sql = " SELECT * FROM itens ORDER BY estoque_at DESC ";

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Itens i = new Itens();
				i.setId(rs.getInt("id"));
				i.setDescricao(rs.getString("descricao"));
				i.setEstoque_at(rs.getInt("estoque_at"));
				list.add(i);
			}

		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return list;
	}

}
