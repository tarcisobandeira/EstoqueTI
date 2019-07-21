package br.com.MBean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.DAO.liDAO;
import br.com.DAO.localizacaoDAO;
import br.com.DAO.trocaDAO;
import br.com.entities.LI;
import br.com.entities.Localizacao;
import br.com.entities.Troca;

@ManagedBean
@ViewScoped
public class TrocaEstoqueMB {

	Troca t = new Troca();
	LI li = new LI();

	liDAO liDAO = new liDAO();
	trocaDAO tDAO = new trocaDAO();
	localizacaoDAO lDAO = new localizacaoDAO();

	List<LI> listLi = new ArrayList<LI>();
	List<Localizacao> listL = new ArrayList<Localizacao>();

	FacesContext context;

	public void fazerMovimento() {
		context = FacesContext.getCurrentInstance();
		if (testarCampos()) {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/yyyy");
			Calendar data = new GregorianCalendar();
			t.setDia(sdf.format(data.getTime()));

			if (tDAO.inserir(t)) {
				if (!liDAO.confirmarLigacao(t)) {
					if (liDAO.inserirTroca(t)) {
						if (liDAO.updateEstoque(descontar(t))) {
							System.out.println("EstoqueTI:Troca de estoque realizada.");
							context.addMessage(null, new FacesMessage("Sucesso",
									"Troca de " + t.getItens().getDescricao() + " foi feita."));
							zerar();
						} else {
							System.out.println("EstoqueTI:Erro ao descontar no estoque.");
							context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Erro Fatal",
									"Erro ao se conectar com o servidor."));
						}
					} else {
						System.out.println("EstoqueTI:Erro ao fazer ligação.");
						context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Erro Fatal",
								"Erro ao se conectar com o servidor."));
					}
				} else {
					if (liDAO.updateEstoque(acrescentar(t))) {
						if (liDAO.updateEstoque(descontar(t))) {
							System.out.println("EstoqueTI:Troca de estoque realizada.");
							context.addMessage(null, new FacesMessage("Sucesso",
									"Troca de " + t.getItens().getDescricao() + " foi feita."));
							zerar();
						} else {
							System.out.println("EstoqueTI:Erro ao descontar no estoque.");
							context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Erro Fatal",
									"Erro ao se conectar com o servidor."));
						}
					} else {
						System.out.println("EstoqueTI:Erro ao acrescentar no estoque.");
						context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Erro Fatal",
								"Erro ao se conectar com o servidor."));
					}
				}
			} else {
				System.out.println("EstoqueTI:Erro ao trocar de local.");
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Erro Fatal",
						"Erro ao se conectar com o servidor."));
			}
		} else {
			System.out.println("EstoqueTI:Campo vazio em troca de estoque.");
			context.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Campo Vazio.", "Algum campo não foi preenchido."));
		}
	}

	public LI descontar(Troca tr) {
		li = liDAO.buscarEstoque(tr.getId_itens(), tr.getId_localAn());
		li.setEstoque(li.getEstoque() - tr.getQuantidade());
		return li;
	}

	public LI acrescentar(Troca tr) {
		li = liDAO.buscarEstoque(tr.getId_itens(), tr.getId_localAt());
		li.setEstoque(li.getEstoque() + tr.getQuantidade());
		return li;
	}

	public void listarLocal() {
		listLi = new ArrayList<LI>();
		listLi = liDAO.listarLocalSemFalta(t.getId_itens());
	}

	public void listarDisponivel() {
		if (t.getId_localAn() != null) {
			listL = lDAO.lDisponivel(t.getId_localAn());
			li = liDAO.buscarEstoque(t.getId_itens(), t.getId_localAn());
		}
	}

	public boolean testarCampos() {
		if (t.getId_itens() == null || t.getId_localAn() == null || t.getId_localAt() == null
				|| t.getQuantidade() == null) {
			return false;
		} else {
			return true;
		}
	}

	public void zerar() {
		t = new Troca();
		li = new LI();
		listLi = new ArrayList<LI>();
		listL = new ArrayList<Localizacao>();
	}

	public Troca getT() {
		return t;
	}

	public void setT(Troca t) {
		this.t = t;
	}

	public liDAO getLiDAO() {
		return liDAO;
	}

	public void setLiDAO(liDAO liDAO) {
		this.liDAO = liDAO;
	}

	public List<LI> getListLi() {
		return listLi;
	}

	public void setListLi(List<LI> listLi) {
		this.listLi = listLi;
	}

	public LI getLi() {
		return li;
	}

	public void setLi(LI li) {
		this.li = li;
	}

	public trocaDAO gettDAO() {
		return tDAO;
	}

	public void settDAO(trocaDAO tDAO) {
		this.tDAO = tDAO;
	}

	public localizacaoDAO getlDAO() {
		return lDAO;
	}

	public void setlDAO(localizacaoDAO lDAO) {
		this.lDAO = lDAO;
	}

	public List<Localizacao> getListL() {
		return listL;
	}

	public void setListL(List<Localizacao> listL) {
		this.listL = listL;
	}
}
