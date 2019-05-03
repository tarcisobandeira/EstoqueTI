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
	Funcionarios fid = new Funcionarios();
	Funcionarios login = new Funcionarios();
	Funcionarios selc;

	public String salvar() {
		fid = fDAO.buscarFuncionarioId(f.getId());
		if (f.getId() != null) {
			Funcionarios funcionarios = fDAO.buscarFuncionarioId(f.getId());
			if (funcionarios != null && funcionarios.getId().equals(f.getId())) {
				if(editarFuncionario()) {
					return "PF('dlg11').hide();";
				}
			}
		} else {
			criarFuncionario();
		}
		return null;
	}

	public void criarFuncionario() {
		if (testarCampos()) {
			if (fid == null) {
				if (fDAO.inserir(f)) {
					System.out.println("EstoqueTI:Funcionario criado.");
					zerar();
				} else {
					System.out.println("EstoqueTI:Erro ao criar funcionario.");
				}
			} else {
				System.out.println("EstoqueTI:Login já existe.");
			}
		} else {
			System.out.println("EstoqueTI:Campo vazio em funcionarios.");
		}
	}

	public boolean editarFuncionario() {
		if(testarCampos()) {
			if(!fDAO.confirmNome(f.getNome(), f.getId()) ) {
				if(!fDAO.confirmLogin(f.getLogin(), f.getId())) {
					if(fDAO.editar(f)) {
						System.out.println("EstoqueTI:Funcionario " + f.getNome() + " editado.");
						zerar();
						return true;
					}else {
						System.out.println("EstoqueTI:Erro 4");
					}
				}else {
					System.out.println("EstoqueTI:Erro 3");
				}
			}else {
				System.out.println("EstoqueTI:Erro 2");
			}
		}else {
			System.out.println("EstoqueTI:Erro 1");
		}
		return false;
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
		fid = new Funcionarios();
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
