package br.com.MBean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.DAO.funcionariosDAO;
import br.com.entities.Funcionarios;

@ManagedBean
@ViewScoped
public class FuncionariosMB {

	funcionariosDAO fDAO = new funcionariosDAO();
	Funcionarios f = new Funcionarios();

	public void criarFuncionario() {
		if (testarCampos()) {
			if (fDAO.inserir(f)) {
				System.out.println("EstoqueTI:Funcionario criado.");
				zerar();
			} else {
				System.out.println("EstoqueTI:Erro ao criar funcionario.");
			}
		} else {
			System.out.println("EstoqueTI:Campo vazio em funcionarios.");
		}
	}

	public boolean testarCampos() {
		if (f.getNome().equals("") || f.getFuncao() == null || f.getLogin().equals("") || f.getSenha().equals("")) {
			return false;
		} else {
			return true;
		}
	}
	
	public void zerar() {
		f = new Funcionarios();
	}

	public funcionariosDAO getfDAO() {
		return fDAO;
	}

	public void setfDAO(funcionariosDAO fDAO) {
		this.fDAO = fDAO;
	}

	public Funcionarios getF() {
		return f;
	}

	public void setF(Funcionarios f) {
		this.f = f;
	}

}
