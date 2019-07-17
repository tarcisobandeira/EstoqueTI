package br.com.MBean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.DAO.localizacaoDAO;
import br.com.entities.Localizacao;

@ManagedBean
@ViewScoped
public class LocalizacaoMB {

	localizacaoDAO lDAO = new localizacaoDAO();
	Localizacao l = new Localizacao();
	Localizacao selcN = new Localizacao();
	Localizacao selcF = new Localizacao();

	FacesContext context;

	public void criarLocal() {
		context = FacesContext.getCurrentInstance();
		if (testarCampos()) {
			if (lDAO.buscarLocalNome(l.getLocal_nome())) {
				if (lDAO.inserir(l)) {
					System.out.println("EstoqueTI:Local criado.");
					context.addMessage(null, new FacesMessage("Sucesso",
							"Local " + l.getLocal_nome() + " foi criado para o/a " + texto()));
					zerar();
				} else {
					System.out.println("EstoqueTI:Erro ao criar local.");
					context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Erro Fatal",
							"Erro ao se conectar com o servidor."));
				}
			} else {
				System.out.println("EstoqueTI:Esse local já existe.");
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Local Repetido",
						"Esse local já está cadastrado."));
			}
		} else {
			System.out.println("EstoqueTI:Campo vazio em localização.");
			context.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Campo Vazio.", "Algum campo não foi preenchido."));
		}
	}

	public boolean testarCampos() {
		if (l.getLocal_nome().equals("") || l.getLocalNF() == null) {
			return false;
		} else {
			return true;
		}
	}

	public String texto() {
		if (l.getLocalNF() == 1) {
			return "Núcleo.";
		} else {
			return "FAJ.";
		}
	}

	public void zerar() {
		l = new Localizacao();
	}

	public localizacaoDAO getlDAO() {
		return lDAO;
	}

	public void setlDAO(localizacaoDAO lDAO) {
		this.lDAO = lDAO;
	}

	public Localizacao getL() {
		return l;
	}

	public void setL(Localizacao l) {
		this.l = l;
	}

	public Localizacao getSelcN() {
		return selcN;
	}

	public void setSelcN(Localizacao selcN) {
		this.selcN = selcN;
	}

	public Localizacao getSelcF() {
		return selcF;
	}

	public void setSelcF(Localizacao selcF) {
		this.selcF = selcF;
	}

}
