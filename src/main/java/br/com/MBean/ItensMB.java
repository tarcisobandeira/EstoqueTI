package br.com.MBean;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.DAO.entradaDAO;
import br.com.DAO.itensDAO;
import br.com.DAO.localizacaoDAO;
import br.com.entities.Entrada;
import br.com.entities.Itens;

@ManagedBean
@ViewScoped
public class ItensMB {

	localizacaoDAO lDAO = new localizacaoDAO();
	itensDAO iDAO = new itensDAO();
	entradaDAO eDAO = new entradaDAO();
	Itens i = new Itens();
	Itens selc;
	Itens is = new Itens();
	Entrada en = new Entrada();

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
					if (addEntrada()) {
						System.out.println("EstoqueTI:Feito a entrada do item.");
						zerar();
					} else {
						System.out.println("EstoqueTI:Erro ao dar entrada no item.");
					}
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
		if (iDAO.editar(i)) {
			System.out.println("EstoqueTI:Item modificado.");
			zerar();
		} else {
			System.out.println("EstoqueTI:Erro ao editar o item.");
		}
	}

	public boolean testarCampos() {
		if ((i.getDescricao().equals("")) || (i.getUnidade() == null) || (i.getMinimo() == null)
				|| (i.getSaldo_ini() == null) || (i.getId_localizacao() == null)) {
			return false;
		} else {
			return true;
		}
	}

	public boolean addEntrada() {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/yyyy");
		Calendar data = new GregorianCalendar();
		en.setDia(sdf.format(data.getTime()));
		en.setEntrada(i.getSaldo_ini());
		i = iDAO.buscarItemDescricao(i.getDescricao());
		en.setId_itens(i.getId());
		en.setId_localizacao(i.getId_localizacao());
		
		if(en.getCodigo() == null ) {
			en.setCodigo(0);
		}

		if (eDAO.inserir(en)) {
			return true;
		} else {
			return false;
		}

	}

	public void editar() {
		i = selc;
	}

	public void zerar() {
		i = new Itens();
		is = new Itens();
		en = new Entrada();
		selc = new Itens();
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

	public entradaDAO geteDAO() {
		return eDAO;
	}

	public void seteDAO(entradaDAO eDAO) {
		this.eDAO = eDAO;
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

	public Entrada getEn() {
		return en;
	}

	public void setEn(Entrada en) {
		this.en = en;
	}

}
