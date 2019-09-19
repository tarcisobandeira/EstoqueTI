package br.com.MBean;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.DAO.funcionariosDAO;
import br.com.entities.Funcionarios;

@ManagedBean
@SessionScoped
public class FuncionariosMB {

	funcionariosDAO fDAO = new funcionariosDAO();
	Funcionarios f = new Funcionarios();
	Funcionarios selc;

	List<Funcionarios> fun;

	FacesContext context;

	public void salvar() {
		context = FacesContext.getCurrentInstance();
		if (f.getId() != null) {
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
						context.addMessage(null,
								new FacesMessage("Sucesso", "Funcionario " + f.getNome() + " foi criado."));
						zerar();
					} else {
						System.out.println("EstoqueTI:Erro ao criar.");
						context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Erro Fatal",
								"Erro ao se conectar com o servidor."));
					}
				} else {
					System.out.println("EstoqueTI:Erro de login repetido.");
					context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Login Repetido",
							"Esse login já está sendo usado por outro funcionario."));
				}
			} else {
				System.out.println("EstoqueTI:Erro de nome repetido.");
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Nome Repetido",
						"Esse nome já está sendo usado por outro funcionario."));
			}
		} else {
			System.out.println("EstoqueTI:Algum campo vazio.");
			context.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Campo Vazio.", "Algum campo não foi preenchido."));
		}
	}

	public void editarFuncionario() {
		if (testarCampos()) {
			if (!fDAO.confirmNome(f.getNome(), f.getId())) {
				if (!fDAO.confirmLogin(f.getLogin(), f.getId())) {
					if (fDAO.editar(f)) {
						System.out.println("EstoqueTI:Funcionario " + f.getNome() + " editado.");
						context.addMessage(null,
								new FacesMessage("Sucesso", "Funcionario " + f.getNome() + " foi editado."));
						zerar();
					} else {
						System.out.println("EstoqueTI:Erro ao editar.");
						context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Erro Fatal",
								"Erro ao se conectar com o servidor."));
					}
				} else {
					System.out.println("EstoqueTI:Erro de login repetido.");
					context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Login Repetido",
							"Esse login já está sendo usado por outro funcionario."));
				}
			} else {
				System.out.println("EstoqueTI:Erro de nome repetido.");
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Nome Repetido",
						"Esse nome já está sendo usado por outro funcionario."));
			}
		} else {
			System.out.println("EstoqueTI:Algum campo vazio.");
			context.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Campo Vazio.", "Algum campo não foi preenchido."));
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
		selc = null;
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

	public List<Funcionarios> getFun() {
		return fun;
	}

	public void setFun(List<Funcionarios> fun) {
		this.fun = fun;
	}

	public FacesContext getContext() {
		return context;
	}

	public void setContext(FacesContext context) {
		this.context = context;
	}

}
