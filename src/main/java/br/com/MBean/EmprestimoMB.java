package br.com.MBean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.DAO.emprestimoDAO;
import br.com.DAO.itensDAO;
import br.com.DAO.liDAO;
import br.com.entities.Emprestimo;
import br.com.entities.Itens;
import br.com.entities.LI;

@ManagedBean
@ViewScoped
public class EmprestimoMB {

	List<Date> dia = new ArrayList<Date>();
	List<LI> listLi = new ArrayList<LI>();
	List<Emprestimo> listEm = new ArrayList<Emprestimo>();

	Emprestimo em = new Emprestimo();
	LI li = new LI();
	Date minDate = new Date();
	Itens i = new Itens();

	liDAO liDAO = new liDAO();
	emprestimoDAO emDAO = new emprestimoDAO();
	itensDAO iDAO = new itensDAO();

	Emprestimo selc;

	public EmprestimoMB() {
		privarData();
	}

	public void privarData() {
		Date hoje = new Date();
		long oneDay = 24 * 60 * 60 * 1000;
		setMinDate(new Date(hoje.getTime() - oneDay));
	}

	public void fazerEmprestimo() {
		salvarData();
		em.setLimite(0);

		i = iDAO.buscarItem(em.getId_itens());
		i.setEstoque_at(i.getEstoque_at() - em.getQuantidade());
		if (testarCampo()) {
			if (emDAO.inserir(em)) {
				if (liDAO.updateEstoque(descontar(em))) {
					if (iDAO.updateEstoque(i.getEstoque_at(), em.getId_itens())) {
						System.out.println("EstoqueTI:Empréstimo feito.");
						zerar();
					} else {
						System.out.println("EstoqueTI:Erro ao descontar no item.");
					}
				} else {
					System.out.println("EstoqueTI:Erro ao descontar no estoque.");
				}
			} else {
				System.out.println("EstoqueTI:Erro ao fazer empréstimo.");
			}
		} else {
			System.out.println("EstoqueTI:Campo vazio em empréstimo.");
		}
	}

	public void fecharEmprestimo() {
		selc.setLimite(3);
		i = iDAO.buscarItem(selc.getId());
		i.setEstoque_at(i.getEstoque_at() + selc.getQuantidade());
		if (emDAO.updateLimite(selc)) {
			if (liDAO.updateEstoque(acrescentar(selc))) {
				if (iDAO.updateEstoque(i.getEstoque_at(), selc.getId_itens())) {
					System.out.println("EstoqueTI:Empréstimo finalizado.");
					zerar();
				} else {
					System.out.println("EstoqueTI:Erro ao acrescentar no itens.");
				}
			} else {
				System.out.println("EstoqueTI:Erro ao acrescentar no estoque.");
			}
		} else {
			System.out.println("EstoqueTI:Erro ao finalizar empréstimo.");
		}
	}

	public void salvarData() {
		int i = 1;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/yyyy");
		for (Date d : dia) {
			if (i == 1) {
				em.setDia_saida(sdf.format(d));
			} else {
				em.setDia_devol(sdf.format(d));
			}
			i++;
		}
	}

	public LI descontar(Emprestimo emp) {
		li = liDAO.buscarEstoque(emp.getId_itens(), emp.getId_localizacao());
		li.setEstoque(li.getEstoque() - emp.getQuantidade());
		return li;
	}

	public LI acrescentar(Emprestimo emp) {
		li = liDAO.buscarEstoque(emp.getId_itens(), emp.getId_localizacao());
		li.setEstoque(li.getEstoque() + emp.getQuantidade());
		return li;
	}

	public boolean testarCampo() {
		if ((em.getId_itens() == null) || (li.getId_localizacao() == null) || (em.getQuantidade() == null)
				|| (em.getDia_saida() == null) || (em.getDia_devol() == null)) {
			return false;
		} else {
			return true;
		}
	}

	public void detalhes() {
		em = selc;
	}

	public void listarLocal() {
		listLi = new ArrayList<LI>();
		listLi = liDAO.listarLocal(em.getId_itens());
	}

	public void listarTotal() {
		li.setEstoque(0);
		em.setQuantidade(0);
		if (em.getId_localizacao() != null) {
			li = liDAO.buscarEstoque(em.getId_itens(), em.getId_localizacao());
		}
	}

	public void zerar() {
		dia = new ArrayList<Date>();
		li = new LI();
		em = new Emprestimo();
		listLi = new ArrayList<LI>();
		selc = null;
	}

	public List<Date> getDia() {
		return dia;
	}

	public void setDia(List<Date> dia) {
		this.dia = dia;
	}

	public List<LI> getListLi() {
		return listLi;
	}

	public void setListLi(List<LI> listLi) {
		this.listLi = listLi;
	}

	public Emprestimo getEm() {
		return em;
	}

	public void setEm(Emprestimo em) {
		this.em = em;
	}

	public LI getLi() {
		return li;
	}

	public void setLi(LI li) {
		this.li = li;
	}

	public liDAO getLiDAO() {
		return liDAO;
	}

	public void setLiDAO(liDAO liDAO) {
		this.liDAO = liDAO;
	}

	public emprestimoDAO getEmDAO() {
		return emDAO;
	}

	public void setEmDAO(emprestimoDAO emDAO) {
		this.emDAO = emDAO;
	}

	public Date getMinDate() {
		return minDate;
	}

	public void setMinDate(Date minDate) {
		this.minDate = minDate;
	}

	public Itens getI() {
		return i;
	}

	public void setI(Itens i) {
		this.i = i;
	}

	public itensDAO getiDAO() {
		return iDAO;
	}

	public void setiDAO(itensDAO iDAO) {
		this.iDAO = iDAO;
	}

	public Emprestimo getSelc() {
		return selc;
	}

	public void setSelc(Emprestimo selc) {
		this.selc = selc;
	}

}
