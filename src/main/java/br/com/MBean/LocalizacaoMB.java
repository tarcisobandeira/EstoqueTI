package br.com.MBean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.DAO.localizacaoDAO;
import br.com.entities.Localizacao;

@ManagedBean
@ViewScoped
public class LocalizacaoMB {

	localizacaoDAO lDAO = new localizacaoDAO();
	Localizacao l = new Localizacao();
	Localizacao selcN = new Localizacao();
	Localizacao selcF = new Localizacao();

	public void criarLocal() {
		if (testarCampos()) {
			if (lDAO.buscarLocalNome(l.getLocal_nome())) {
				if (lDAO.inserir(l)) {
					System.out.println("EstoqueTI:Local criado.");
					zerar();
				} else {
					System.out.println("EstoqueTI:Erro ao criar local.");
				}
			} else {
				System.out.println("EstoqueTI:Esse local já existe.");
			}
		} else {
			System.out.println("EstoqueTI:Campo vazio em localização.");
		}
	}

	public boolean testarCampos() {
		if (l.getLocal_nome().equals("") || l.getLocalNF() == null) {
			return false;
		} else {
			return true;
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
