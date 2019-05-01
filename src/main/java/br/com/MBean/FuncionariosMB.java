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
	Funcionarios selc;
	
	public void salvar() {
		if(f.getId() == null) {
			Funcionarios funcionarios = fDAO.buscarFuncionarioId(f.getId());
			if(funcionarios != null && funcionarios.getId().equals(f.getId())) {
				editarFuncionario();
			}
		}else {
			criarFuncionario();
		}
	}
	
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
	
	public void editarFuncionario() {
		if(testarCampos()) {
			if(fDAO.editar(f)) {
				System.out.println("EstoqueTI:Funcionario editado.");
			}
		}else {
			System.out.println("EstoqueTI:Erro ao editar funcionario.");
		}
	}

	public boolean testarCampos() {
		if (f.getNome().equals("") || f.getFuncao() == null || f.getLogin().equals("") || f.getSenha().equals("")) {
			return false;
		} else {
			return true;
		}
	}
	
	public void editar() {
		f = selc;
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

	public Funcionarios getSelc() {
		return selc;
	}

	public void setSelc(Funcionarios selc) {
		this.selc = selc;
	}

}
