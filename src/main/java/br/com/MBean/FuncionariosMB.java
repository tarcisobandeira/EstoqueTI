package br.com.MBean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.DAO.funcionariosDAO;
import br.com.entities.Funcionarios;

@ManagedBean
@SessionScoped
public class FuncionariosMB {

	funcionariosDAO fDAO = new funcionariosDAO();
	Funcionarios f = new Funcionarios();
	Funcionarios ef = new Funcionarios();
	Funcionarios selc;

	public void salvar() {
		if (f.getId() == null) {
			editarFuncionario();
		} else {
			criarFuncionario();
		}
	}

	public void criarFuncionario() {
		if (testarCampos()) {
			if (!fDAO.confirmNome(f.getNome(), 0)) {
				if (!fDAO.confirmLogin(f.getLogin(), 0)) {
					if (fDAO.inserir(f)) {
						System.out.println("EstoqueTI:Funcionario " + f.getNome() + " criado.");
						zerar();
					} else {
						System.out.println("EstoqueTI:Erro 4");
					}
				} else {
					System.out.println("EstoqueTI:Erro 3");
				}
			} else {
				System.out.println("EstoqueTI:Erro 2");
			}
		} else {
			System.out.println("EstoqueTI:Erro 1");
		}
	}

	public void editarFuncionario() {
		if (!fDAO.confirmNome(ef.getNome(), ef.getId())) {
			if (!fDAO.confirmLogin(ef.getLogin(), ef.getId())) {
				if (fDAO.editar(ef)) {
					System.out.println("EstoqueTI:Funcionario " + ef.getNome() + " editado.");
					zerar();
				} else {
					System.out.println("EstoqueTI:Erro ao editar.");
				}
			} else {
				System.out.println("EstoqueTI:Erro no login.");
			}
		} else {
			System.out.println("EstoqueTI:Erro no nome.");
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
		ef = selc;
	}

	public void zerar() {
		f = new Funcionarios();
		ef = new Funcionarios();
		selc = new Funcionarios();
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

	public Funcionarios getEf() {
		return ef;
	}

	public void setEf(Funcionarios ef) {
		this.ef = ef;
	}

}
