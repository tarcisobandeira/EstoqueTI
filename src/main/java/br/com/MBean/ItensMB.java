package br.com.MBean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.DAO.itensDAO;
import br.com.DAO.localizacaoDAO;
import br.com.entities.Itens;

@ManagedBean
@ViewScoped
public class ItensMB {

	localizacaoDAO lDAO = new localizacaoDAO();
	itensDAO iDAO = new itensDAO();
	Itens i = new Itens();
	Itens selc;
	Itens is = new Itens();

	public void salvar() {
		if (i.getId() != null) {
			Itens itens = iDAO.buscarItem(i.getId());
			if (itens != null && itens.getId().equals(i.getId())) {
				editarItem();
			}
		} else {
			criarItem();
		}
	}

	public void criarItem() {
		if (testarCampos()) {
			is = iDAO.buscarItemDescricao(i.getDescricao());
			if (is == null) {
				if (iDAO.inserir(i)) {
					System.out.println("EstoqueTI:Item criado.");
					zerar();
				} else {
					System.out.println("EstoqueTI:Erro ao criar item.");
				}
			} else {
				System.out.println("EstoqueTI:Item já foi criado.");
			}
		} else {
			System.out.println("EstoqueTI:Campo vazio em Itens.");
		}
	}

	public void editarItem() {
		if (testarCamposE()) {
			if (iDAO.editar(i)) {
				System.out.println("EstoqueTI:Item modificado.");
				zerar();
			} else {
				System.out.println("EstoqueTI:Erro ao editar o item.");
			}
		} else {
			System.out.println("EstoqueTI:Campo vazio em editar itens.");
		}
	}

	public boolean testarCampos() {
		if ((i.getDescricao().equals("")) || (i.getUnidade() == null) || (i.getMinimo() == null)
				|| (i.getId_localizacao() == null)) {
			return false;
		} else {
			return true;
		}
	}

	public boolean testarCamposE() {
		if ((i.getDescricao().equals("")) || (i.getUnidade() == null) || (i.getMinimo() == null)) {
			return false;
		} else {
			return true;
		}
	}

	public void editar() {
		i = selc;
	}

	public void zerar() {
		i = new Itens();
		is = new Itens();
		selc = null;
	}

	public itensDAO getiDAO() {
		return iDAO;
	}

	public void setiDAO(itensDAO iDAO) {
		this.iDAO = iDAO;
	}

	public Itens getI() {
		return i;
	}

	public void setI(Itens i) {
		this.i = i;
	}

	public localizacaoDAO getlDAO() {
		return lDAO;
	}

	public void setlDAO(localizacaoDAO lDAO) {
		this.lDAO = lDAO;
	}

	public Itens getSelc() {
		return selc;
	}

	public void setSelc(Itens selc) {
		this.selc = selc;
	}

	public Itens getIs() {
		return is;
	}

	public void setIs(Itens is) {
		this.is = is;
	}

}
