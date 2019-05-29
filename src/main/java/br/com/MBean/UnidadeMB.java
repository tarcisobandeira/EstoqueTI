package br.com.MBean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.DAO.unidadeDAO;
import br.com.entities.Unidade;

@ManagedBean
@ViewScoped
public class UnidadeMB {

	Unidade u = new Unidade();
	unidadeDAO uDAO = new unidadeDAO();

	public void criarUnidade() {
		if (testarCampo()) {
			if (uDAO.inserir(u)) {
				System.out.println("EstoqueTI:Unidade criada.");
				zerar();
			} else {
				System.out.println("EstoqueTI:Erro ao criar no banco.");
			}
		} else {
			System.out.println("EstoqueTI:Campo vazio em Unidade.");
		}
	}

	public boolean testarCampo() {
		if (u.getUnidade().equals("")) {
			return false;
		} else {
			return true;
		}
	}

	public void zerar() {
		u = new Unidade();
	}

	public Unidade getU() {
		return u;
	}

	public void setU(Unidade u) {
		this.u = u;
	}

	public unidadeDAO getuDAO() {
		return uDAO;
	}

	public void setuDAO(unidadeDAO uDAO) {
		this.uDAO = uDAO;
	}

}
