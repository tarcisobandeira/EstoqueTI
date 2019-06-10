package br.com.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.entities.Itens;
import br.com.entities.LI;
import br.com.entities.Localizacao;
import br.com.entities.Troca;
import br.com.jdbc.ConnectionDB;

public class liDAO {

	Connection con;

	public liDAO() {
		con = ConnectionDB.getConnection();
	}

	public boolean inserir(int id1, int id2) {
		String sql = " INSERT INTO LI (id_itens, id_localizacao, estoque) VALUES (?,?,?) ";

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id1);
			ps.setInt(2, id2);
			ps.setInt(3, 0);

			if (ps.executeUpdate() == 1) {
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean confirmarLigacao(Troca t) {
		String sql = " SELECT * FROM LI WHERE id_itens = ? AND id_localizacao = ? ";

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, t.getId_itens());
			ps.setInt(2, t.getId_localAt());
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean inserirTroca(Troca t) {
		String sql = " INSERT INTO LI (id_itens, id_localizacao, estoque) VALUES (?,?,?) ";

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, t.getId_itens());
			ps.setInt(2, t.getId_localAt());
			ps.setInt(3, t.getQuantidade());

			if (ps.executeUpdate() == 1) {
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean updateEstoque(LI li) {
		String sql = " UPDATE LI SET estoque = ? WHERE id_localizacao = ? AND id_itens = ? ";
		
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, li.getEstoque());
			ps.setInt(2, li.getId_localizacao());
			ps.setInt(3, li.getId_itens());
			
			if(ps.executeUpdate() == 1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public List<LI> listarLocal(int id) {
		List<LI> list = new ArrayList<LI>();
		String sql = " SELECT li.*, i.descricao AS nomeItens, l.local_nome AS nomeLocal " + " FROM LI li "
				+ " INNER JOIN itens i " + " ON li.id_itens = i.id " + " INNER JOIN localizacao l "
				+ " ON li.id_localizacao = l.id " + " WHERE li.id_itens = ? AND li.estoque != 0 ";
		
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				LI l = new LI();
				l.setId(rs.getInt("id"));
				l.setId_itens(rs.getInt("id_itens"));
				l.setId_localizacao(rs.getInt("id_localizacao"));
				l.setEstoque(rs.getInt("estoque"));
				l.setItens(new Itens(l.getId_itens(), rs.getString("nomeItens"), null, null, null));
				l.setLocalizacao(new Localizacao(l.getId_localizacao(), rs.getString("nomeLocal"), null));
				
				list.add(l);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	public LI buscarEstoque(int id1, int id2) {
		String sql = "SELECT * FROM LI WHERE id_itens = ? AND id_localizacao = ? ";

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id1);
			ps.setInt(2, id2);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				LI ll = new LI();
				ll.setId(rs.getInt("id"));
				ll.setId_itens(id1);
				ll.setId_localizacao(id2);
				ll.setEstoque(rs.getInt("estoque"));
				return ll;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
