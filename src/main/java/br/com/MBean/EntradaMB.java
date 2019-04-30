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

	public void fazerEntrada() {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/yyyy");
		Calendar data = new GregorianCalendar();
		en.setDia(sdf.format(data.getTime()));

		i = iDAO.buscarItem(en.getId_itens());
		i.setEstoque_at(i.getEstoque_at() + en.getEntrada());

		en.setId_localizacao(l.getId());

		if (eDAO.inserir(en)) {
			System.out.println("EstoqueTI:Foi feita a entrada de " + i.getDescricao() + ".");
			if (iDAO.updateEstoque(i.getEstoque_at(), i.getId())) {
				System.out.println("EstoqueTI:Estoque atualizado.");
				zerar();
			} else {
				System.out.println("EstoqueTI:Erro ao atualizar estoque.");
			}
		} else {
			System.out.println("EstoqueTI:Erro ao fazer a entrar do item.");
		}
	}

	public void fazerMultEntrada() {
		for (Entrada entrada : listE) {

			i = iDAO.buscarItem(entrada.getId_itens());
			i.setEstoque_at(i.getEstoque_at() + entrada.getEntrada());

			entrada.setCodigo(codigo);

			if (eDAO.inserirMult(entrada)) {
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
		zerar();
		zerarList();
	}

	public void addListE() {
		Entrada entrada = new Entrada();
		i = iDAO.buscarItem(en.getId_itens());

		entrada.setItens(i);
		entrada.setEntrada(en.getEntrada());
		entrada.setId_itens(en.getId_itens());
		entrada.setId_localizacao(l.getId());

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/yyyy");
		Calendar data = new GregorianCalendar();
		entrada.setDia(sdf.format(data.getTime()));

		listE.add(entrada);

		entrada = new Entrada();
		zerar();
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
	}

	public void zerarList() {
		listE = new ArrayList<Entrada>();
		codigo = null;
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

}
