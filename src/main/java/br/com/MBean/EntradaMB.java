package br.com.MBean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.DAO.entradaDAO;
import br.com.DAO.itensDAO;
import br.com.DAO.localizacaoDAO;
import br.com.entities.Entrada;
import br.com.entities.Itens;
import br.com.entities.Localizacao;

@ManagedBean
@ViewScoped
public class EntradaMB {

	entradaDAO eDAO = new entradaDAO();
	itensDAO iDAO = new itensDAO();
	localizacaoDAO lDAO = new localizacaoDAO();
	Entrada en = new Entrada();
	Itens i = new Itens();
	Localizacao l = new Localizacao();
	List<Entrada> listE = new ArrayList<Entrada>();
	Integer codigo;
	Entrada selc;

	public void fazerEntrada() {
		if (testarCampos()) {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/yyyy");
			Calendar data = new GregorianCalendar();
			en.setDia(sdf.format(data.getTime()));

			i = iDAO.buscarItem(en.getId_itens());
			i.setEstoque_at(i.getEstoque_at() + en.getEntrada());

			en.setId_localizacao(l.getId());

			if (en.getCodigo() == null) {
				en.setCodigo(0);
			}

			if (eDAO.inserir(en)) {
				System.out.println("EstoqueTI:Foi feita a entrada de " + i.getDescricao() + ".");
				if (iDAO.updateEstoque(i.getEstoque_at(), i.getId())) {
					System.out.println("EstoqueTI:Estoque atualizado.");
					zerar();
					codigo = null;
				} else {
					System.out.println("EstoqueTI:Erro ao atualizar estoque.");
				}
			} else {
				System.out.println("EstoqueTI:Erro ao fazer a entrar do item.");
			}
		} else {
			System.out.println("EstoqueTI:Campo vazio em entrada single.");
		}
	}

	public void fazerMultEntrada() {
		for (Entrada entrada : listE) {

			i = iDAO.buscarItem(entrada.getId_itens());
			i.setEstoque_at(i.getEstoque_at() + entrada.getEntrada());

			if (eDAO.inserir(entrada)) {
				System.out.println("EstoqueTI:Foi feita a entrada de " + entrada.getItens().getDescricao() + ".");
				if (iDAO.updateEstoque(i.getEstoque_at(), i.getId())) {
					System.out.println("EstoqueTI:Estoque atualizado.");
				} else {
					System.out.println("EstoqueTI:Erro ao atualizar estoque.");
				}
			} else {
				System.out.println("EstoqueTI:Erro ao fazer a entrar do item.");
			}
		}
		System.out.println("EstoqueTI:Nenhum item na lista.");
		zerar();
		zerarList();
		codigo = null;
	}

	public void addListE() {
		if (testarCampos()) {
			Entrada entrada = new Entrada();
			i = iDAO.buscarItem(en.getId_itens());

			entrada.setItens(i);
			entrada.setEntrada(en.getEntrada());
			entrada.setId_itens(en.getId_itens());
			entrada.setId_localizacao(l.getId());

			if (codigo == null || codigo == 0) {
				entrada.setCodigo(0);
			} else {
				entrada.setCodigo(codigo);
			}

			SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/yyyy");
			Calendar data = new GregorianCalendar();
			entrada.setDia(sdf.format(data.getTime()));

			updateList();

			listE.add(entrada);

			entrada = new Entrada();
			zerar();
		} else {

		}
	}

	public boolean updateList() {
		int contador = 0;
		for (Entrada entrada : listE) {
			if (entrada.equals(en)) {
				listE.remove(contador);
				return true;
			}
			contador++;
		}
		return false;
	}

	public boolean deletList() {
		int contador = 0;
		for (Entrada entrada : listE) {
			if (entrada.equals(selc)) {
				listE.remove(contador);
				return true;
			}
			contador++;
		}
		return false;
	}

	public boolean testarCampos() {
		if (en.getId_itens() == null || en.getEntrada() == null) {
			return false;
		} else {
			return true;
		}
	}

	public void listarLocal() {
		int id = en.getId_itens();
		i = iDAO.buscarItem(id);
		l = lDAO.buscarLocal(i.getId_localizacao());
	}

	public void zerar() {
		en = new Entrada();
		i = new Itens();
		l = new Localizacao();
		selc = null;
	}

	public void zerarList() {
		listE = new ArrayList<Entrada>();
		codigo = null;
	}

	public void editar() {
		en = selc;
	}

	public entradaDAO geteDAO() {
		return eDAO;
	}

	public void seteDAO(entradaDAO eDAO) {
		this.eDAO = eDAO;
	}

	public Entrada getEn() {
		return en;
	}

	public void setEn(Entrada en) {
		this.en = en;
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

	public Localizacao getL() {
		return l;
	}

	public void setL(Localizacao l) {
		this.l = l;
	}

	public List<Entrada> getListE() {
		return listE;
	}

	public void setListE(List<Entrada> listE) {
		this.listE = listE;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public Entrada getSelc() {
		return selc;
	}

	public void setSelc(Entrada selc) {
		this.selc = selc;
	}

}
