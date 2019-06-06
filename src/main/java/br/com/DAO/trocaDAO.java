package br.com.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.entities.Itens;
import br.com.entities.Localizacao;
import br.com.entities.Troca;
import br.com.jdbc.ConnectionDB;

public class trocaDAO {

	Connection con;

	public trocaDAO() {
		con = ConnectionDB.getConnection();
	}

	public boolean inserir(Troca t) {
		String sql = " INSERT INTO Troca (id_itens, id_localAn, id_localAt, quantidade, dia) VALUES (?,?,?,?,?) ";

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, t.getId_itens());
			ps.setInt(2, t.getId_localAn());
			ps.setInt(3, t.getId_localAt());
			ps.setInt(4, t.getQuantidade());
			ps.setString(5, t.getDia());

			if (ps.executeUpdate() == 1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean update(Troca t) {
		String sql = " UPDATE Troca SET quantidade = ? WHERE id_itens = ? AND id_local ";
		
	}

	public List<Troca> listarTodos() {
		List<Troca> list = new ArrayList<Troca>();
		String sql = " SELECT t.*, i.descricao AS nomeI, ln.local_nome AS nomeLAN, lt.local_nome AS nomeLAT "
				+ " FROM Troca t " + " INNER JOIN Itens i " + " ON t.id_itens = i.id " + " INNER JOIN localizacao ln "
				+ " ON t.id_localAn = ln.id " + " INNER JOIN localizacao lt " + " ON t.id_localAt = lt.id "
				+ " ORDER BY t.id DESC ";

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Troca t = new Troca();
				t.setId(rs.getInt("id"));
				t.setId_itens(rs.getInt("id_itens"));
				t.setId_localAn(rs.getInt("id_localAn"));
				t.setId_localAt(rs.getInt("id_localAt"));
				t.setQuantidade(rs.getInt("quantidade"));
				t.setDia(rs.getString("dia"));
				t.setItens(new Itens(null, rs.getString("nomeI"), null, null, null));
				t.setLAN(new Localizacao(null, rs.getString("nomeLAN"), null));
				t.setLAT(new Localizacao(null, rs.getString("nomeLAT"), null));
				list.add(t);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

}
