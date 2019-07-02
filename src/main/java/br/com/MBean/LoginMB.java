package br.com.MBean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.DAO.funcionariosDAO;
import br.com.entities.Funcionarios;

@ManagedBean
@SessionScoped
public class LoginMB {

	public String login;
	public String senha;
	funcionariosDAO fDAO;
	Funcionarios f;
	FacesContext context;
	private boolean logado = false;

	public String logar() {
		
		context = FacesContext.getCurrentInstance();
		fDAO = new funcionariosDAO();
		f = fDAO.buscarFuncionario(login);
		
		if(f != null && f.getSenha().equals(senha)) {
			logado = true;
			System.out.println("EstoqueTI:" + f.getNome() + " fez login.");
			return "telaPrincipal?faces-redirect=true";
		}else {
			System.out.println("EstoqueTI:Erro ao fazer login.");
			return null;
		}
		
	}
	
	public String deslogar() {
		logado = false;
		System.out.println("EstoqueTI:" + f.getNome() + " deslogou.");
		f = null;
		return "telaLogin?faces-redirect=true";
	}
	
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
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

	public FacesContext getContext() {
		return context;
	}

	public void setContext(FacesContext context) {
		this.context = context;
	}

	public boolean isLogado() {
		return logado;
	}

	public void setLogado(boolean logado) {
		this.logado = logado;
	}

}
