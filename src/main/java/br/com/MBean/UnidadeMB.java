package br.com.MBean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.DAO.unidadeDAO;
import br.com.entities.Unidade;

@ManagedBean
@ViewScoped
public class UnidadeMB {

	Unidade u = new Unidade();
	Unidade selc;
	unidadeDAO uDAO = new unidadeDAO();

	FacesContext context;

	public void criarUnidade() {
		context = FacesContext.getCurrentInstance();
		if (testarCampo()) {
			if (uDAO.buscarUnidadeNome(u.getUnidade())) {
				if (uDAO.inserir(u)) {
					System.out.println("EstoqueTI:Unidade criada.");
					context.addMessage(null, new FacesMessage("Sucesso",
							"Unidade " + u.getUnidade() + " foi criada." ));
					zerar();
				} else {
					System.out.println("EstoqueTI:Erro ao criar no banco.");
					context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Erro Fatal",
							"Erro ao se conectar com o servidor."));
				}
			} else {
				System.out.println("EstoqueTI:Essa unidade já existe.");
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Unidade Repetido",
						"Essa unidade já está cadastrada."));
			}
		} else {
			System.out.println("EstoqueTI:Campo vazio em Unidade.");
			context.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Campo Vazio.", "Algum campo não foi preenchido."));
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
		selc = null;
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

	public Unidade getSelc() {
		return selc;
	}

	public void setSelc(Unidade selc) {
		this.selc = selc;
	}

}
