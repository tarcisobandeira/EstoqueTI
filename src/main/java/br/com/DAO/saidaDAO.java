package br.com.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.entities.Funcionarios;
import br.com.entities.Itens;
import br.com.entities.Localizacao;
import br.com.entities.Saida;
import br.com.jdbc.ConnectionDB;

public class saidaDAO {

	Connection con;

	public saidaDAO() {
		con = ConnectionDB.getConnection();
	}

	public boolean inserir(Saida s) {
		String sql = " INSERT INTO Saida (id_itens, id_localizacao, id_funcionario, saida, dia, OS) VALUES (?,?,?,?,?,?) ";

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, s.getId_itens());
			ps.setInt(2, s.getId_localizacao());
			ps.setInt(3, s.getId_funcionario());
			ps.setInt(4, s.getSaida());
			ps.setString(5, s.getDia());
			ps.setInt(6, s.getOS());

			if (ps.executeUpdate() == 1) {
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public List<Saida> listarTodos() {
		List<Saida> list = new ArrayList<Saida>();
		String sql = " SELECT s.*, " +
					 " i.descricao AS iDescricao, " +
					 " l.local_nome AS lNome, " +
					 " f.nome AS fNome " +
					 " FROM saida s " +
					 " INNER JOIN itens i " +
					 " ON s.id_itens = i.id " +
					 " INNER JOIN localizacao l " +
					 " ON s.id_localizacao = l.id " +
					 " INNER JOIN funcionarios f " +
					 " ON s.id_funcionario = f.id " +
					 " ORDER BY s.id DESC ";

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Saida s = new Saida();
				s.setId(rs.getInt("id"));
				s.setId_itens(rs.getInt("id_itens"));
				s.setId_localizacao(rs.getInt("id_localizacao"));
				s.setId_funcionario(rs.getInt("id_funcionario"));
				s.setSaida(rs.getInt("saida"));
				s.setDia(rs.getString("dia"));
				s.setOS(rs.getInt("OS"));
				s.setItens(new Itens(s.getId_itens(), rs.getString("iDescricao"), null, null, null, null, null));
				s.setLocalizacao(new Localizacao(s.getId_localizacao(), rs.getString("lNome"), null));
				s.setFuncionarios(new Funcionarios(s.getId_funcionario(), rs.getString("fNome"), null, null, null));
				
				list.add(s);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

}
