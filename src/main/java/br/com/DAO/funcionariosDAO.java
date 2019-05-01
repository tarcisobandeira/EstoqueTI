package br.com.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.entities.Funcionarios;
import br.com.jdbc.ConnectionDB;

public class funcionariosDAO {

	Connection con;

	public funcionariosDAO() {
		con = ConnectionDB.getConnection();
	}
	
	public List<Funcionarios> listarTodos(){
		List<Funcionarios> list = new ArrayList<Funcionarios>();
		String sql = " SELECT * FROM Funcionarios ";
		
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				Funcionarios f = new Funcionarios();
				f.setId(rs.getInt("id"));
				f.setNome(rs.getString("nome"));
				f.setFuncao(rs.getInt("funcao"));
				f.setLogin(rs.getString("login"));
				f.setSenha(rs.getString("senha"));
				list.add(f);
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}

	public Funcionarios buscarFuncionario(String login) {
		String sql = "SELECT * FROM Funcionarios WHERE login = ?";

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, login);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				Funcionarios f = new Funcionarios();
				f.setId(rs.getInt("id"));
				f.setNome(rs.getString("nome"));
				f.setFuncao(rs.getInt("funcao"));
				f.setLogin(rs.getString("login"));
				f.setSenha(rs.getString("senha"));

				return f;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Funcionarios buscarFuncionarioId(int id) {
		String sql = " SELECT * FROM Funcionarios WHERE id = ? ";

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				Funcionarios f = new Funcionarios();
				f.setId(rs.getInt("id"));
				f.setNome(rs.getString("nome"));
				f.setFuncao(rs.getInt("funcao"));
				f.setLogin(rs.getString("login"));
				f.setSenha(rs.getString("senha"));

				return f;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean editar(Funcionarios f) {
		String sql = " UPDATE funcionarios SET nome = ?, funcao = ?, login = ?, senha = ? WHERE id = ? ";
		
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, f.getNome());
			ps.setInt(2, f.getFuncao());
			ps.setString(3, f.getLogin());
			ps.setString(4, f.getSenha());
			ps.setInt(5, f.getId());
			
			if(ps.executeUpdate() == 1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean inserir(Funcionarios f) {
		String sql = " INSERT INTO Funcionarios (nome, funcao, login, senha) VALUES (?,?,?,?) ";

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, f.getNome());
			ps.setInt(2, f.getFuncao());
			ps.setString(3, f.getLogin());
			ps.setString(4, f.getSenha());

			if (ps.executeUpdate() == 1) {
				return true;
			}

		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}

}