package br.com.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.entities.Emprestimo;
import br.com.entities.Itens;
import br.com.entities.Localizacao;
import br.com.jdbc.ConnectionDB;

public class emprestimoDAO {

	Connection con;

	public emprestimoDAO() {
		con = ConnectionDB.getConnection();
	}

	public boolean inserir(Emprestimo em) {
		String sql = " INSERT INTO Emprestimos (dia_saida, dia_devol, colaborador, id_itens, id_localizacao, quantidade, obs, limite) VALUES (?,?,?,?,?,?,?,?) ";

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, em.getDia_saida());
			ps.setString(2, em.getDia_devol());
			ps.setString(3, em.getColaborador());
			ps.setInt(4, em.getId_itens());
			ps.setInt(5, em.getId_localizacao());
			ps.setInt(6, em.getQuantidade());
			ps.setString(7, em.getOBS());
			ps.setInt(8, em.getLimite());

			if (ps.executeUpdate() == 1) {
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public List<Emprestimo> listarTodos() {
		List<Emprestimo> list = new ArrayList<Emprestimo>();
		String sql = "  SELECT em.*, i.descricao AS nomeItem, l.local_nome AS nomeLocal " + " FROM emprestimos em "
				+ " INNER JOIN Itens i " + " ON em.id_itens = i.id " + " INNER JOIN localizacao l "
				+ " ON em.id_localizacao = l.id ";

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Emprestimo em = new Emprestimo();
				em.setId(rs.getInt("id"));
				em.setDia_saida(rs.getString("dia_saida"));
				em.setDia_devol(rs.getString("dia_devol"));
				em.setId_itens(rs.getInt("id_itens"));
				em.setId_localizacao(rs.getInt("id_localizacao"));
				em.setColaborador(rs.getString("colaborador"));
				em.setQuantidade(rs.getInt("quantidade"));
				em.setOBS(rs.getString("obs"));
				em.setItens(new Itens(em.getId_itens(), rs.getString("nomeItem"), null, null, null));
				em.setLocalizacao(new Localizacao(em.getId_localizacao(), rs.getString("nomeLocal"), null));
				
				list.add(em);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

}
