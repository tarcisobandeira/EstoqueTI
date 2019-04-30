package br.com.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.entities.Itens;
import br.com.entities.Localizacao;
import br.com.jdbc.ConnectionDB;

public class itensDAO {

	Connection con;

	public itensDAO() {
		con = ConnectionDB.getConnection();
	}

	public boolean inserir(Itens i) {
		String sql = "INSERT INTO Itens (descricao, unidade, minimo, saldo_ini, estoque_at, id_localizacao) VALUES (?,?,?,?,?,?)";

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, i.getDescricao());
			ps.setInt(2, i.getUnidade());
			ps.setInt(3, i.getMinimo());
			ps.setInt(4, i.getSaldo_ini());
			ps.setInt(5, i.getSaldo_ini());
			ps.setInt(6, i.getId_localizacao());

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
		String sql = " UPDATE Itens SET descricao = ?, unidade = ?, minimo = ?, id_localizacao = ? WHERE id = ? ";
		
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, i.getDescricao());
			ps.setInt(2, i.getUnidade());
			ps.setInt(3, i.getMinimo());
			ps.setInt(4, i.getId_localizacao());
			ps.setInt(5, i.getId());
			
			if(ps.executeUpdate() == 1) {
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
				i.setUnidade(rs.getInt("unidade"));
				i.setMinimo(rs.getInt("minimo"));
				i.setSaldo_ini(rs.getInt("saldo_ini"));
				i.setEstoque_at(rs.getInt("estoque_at"));
				i.setId_localizacao(rs.getInt("id_localizacao"));

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
				i.setUnidade(rs.getInt("unidade"));
				i.setMinimo(rs.getInt("minimo"));
				i.setSaldo_ini(rs.getInt("saldo_ini"));
				i.setEstoque_at(rs.getInt("estoque_at"));
				i.setId_localizacao(rs.getInt("id_localizacao"));

				return i;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Itens> listarItens() {
		List<Itens> list = new ArrayList<Itens>();
		String sql = " SELECT i.*, l.local_nome AS nomeLocal " + " FROM itens i " + " INNER JOIN localizacao l "
				+ " ON i.id_localizacao = l.id ";

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Itens i = new Itens();
				i.setId(rs.getInt("id"));
				i.setDescricao(rs.getString("descricao"));
				i.setUnidade(rs.getInt("unidade"));
				i.setMinimo(rs.getInt("minimo"));
				i.setSaldo_ini(rs.getInt("saldo_ini"));
				i.setEstoque_at(rs.getInt("estoque_at"));
				i.setLocalizacao(new Localizacao(i.getId_localizacao(), rs.getString("nomeLocal"), null));
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
		String sql = " SELECT i.*, l.local_nome AS nomeLocal " + " FROM itens i " + " INNER JOIN localizacao l "
				+ " ON i.id_localizacao = l.id " + " WHERE i.estoque_at <= i.minimo " + " ORDER BY i.estoque_at ASC ";

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Itens i = new Itens();
				i.setId(rs.getInt("id"));
				i.setDescricao(rs.getString("descricao"));
				i.setUnidade(rs.getInt("unidade"));
				i.setMinimo(rs.getInt("minimo"));
				i.setSaldo_ini(rs.getInt("saldo_ini"));
				i.setEstoque_at(rs.getInt("estoque_at"));
				i.setLocalizacao(new Localizacao(i.getId_localizacao(), rs.getString("nomeLocal"), null));
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

}
