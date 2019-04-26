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

	public void criarLocal() {
		if (testarCampos()) {
			if (lDAO.inserir(l)) {
				System.out.println("EstoqueTI:Local criado.");
				zerar();
			}else {
				System.out.println("EstoqueTI:Erro ao criar local.");
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

}
