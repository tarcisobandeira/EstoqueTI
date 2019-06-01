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

			if (ps.executeUpdate() == 1) {
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
				+ " ON li.id_localizacao = l.id " + " WHERE li.id_itens = ? ";
		
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				LI l = new LI();
				l.setId(rs.getInt("id"));
				l.setId_itens(rs.getInt("id_itens"));
				l.setId_localizacao(rs.getInt("id_localizacao"));
				l.setItens(new Itens(l.getId_itens(), rs.getString("nomeItens"), null, null, null));
				l.setLocalizacao(new Localizacao(l.getId_localizacao(), rs.getString("nomeLocal"), null));
				
				list.add(l);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}

}
