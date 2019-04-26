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
	Itens is = new Itens();
	Entrada en = new Entrada();

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

		if (eDAO.inserir(en)) {
			return true;
		} else {
			return false;
		}

	}

	public void zerar() {
		i = new Itens();
		en = new Entrada();
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

}
