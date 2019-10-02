package br.com.MBean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.PrimeFaces;

import br.com.DAO.itensDAO;
import br.com.DAO.liDAO;
import br.com.DAO.localizacaoDAO;
import br.com.entities.Itens;
import br.com.entities.LI;

@ManagedBean
@ViewScoped
public class ItensMB {

	localizacaoDAO lDAO = new localizacaoDAO();
	itensDAO iDAO = new itensDAO();
	liDAO liDAO = new liDAO();

	Itens i = new Itens();
	Itens is = new Itens();
	LI li = new LI();

	List<LI> listLi = new ArrayList<LI>();
	List<Itens> listI = new ArrayList<Itens>();

	List<Itens> gardarBusca;

	FacesContext context;
	Itens selc;

	public ItensMB() {
		listI = iDAO.listarTodos();
	}

	public void salvar() {
		context = FacesContext.getCurrentInstance();
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
					is = iDAO.buscarItemDescricao(i.getDescricao());
					if (liDAO.inserir(is.getId(), li.getId_localizacao())) {
						System.out.println("EstoqueTI:Item criado.");
						context.addMessage(null,
								new FacesMessage("Sucesso", "Item " + is.getDescricao() + " foi criado."));
						zerar();
					} else {
						System.out.println("EstoqueTI:Erro ao fazer a ligação com o local.");
						context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Erro Fatal",
								"Erro ao se conectar com o servidor."));
					}
				} else {
					System.out.println("EstoqueTI:Erro ao criar item.");
					context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Erro Fatal",
							"Erro ao se conectar com o servidor."));
				}
			} else {
				System.out.println("EstoqueTI:Item já foi criado.");
				context.addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_WARN, "Item repetido", "Esse item já foi criado."));
			}
		} else {
			System.out.println("EstoqueTI:Campo vazio em Itens.");
			context.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Campo Vazio.", "Algum campo não foi preenchido."));
		}
	}

	public void editarItem() {
		if (testarCamposE()) {
			if (iDAO.editar(i)) {
				System.out.println("EstoqueTI:Item modificado.");
				context.addMessage(null, new FacesMessage("Sucesso", "Item " + i.getDescricao() + " foi editado."));
				zerar();
			} else {
				System.out.println("EstoqueTI:Erro ao editar o item.");
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Erro Fatal",
						"Erro ao se conectar com o servidor."));
			}
		} else {
			System.out.println("EstoqueTI:Campo vazio em editar itens.");
			context.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Campo Vazio.", "Algum campo não foi preenchido."));
		}
	}

	public boolean testarCampos() {
		if ((i.getDescricao().equals("")) || (i.getId_unidade() == null) || (i.getMinimo() == null)) {
			return false;
		} else {
			return true;
		}
	}

	public boolean testarCamposE() {
		if ((i.getDescricao().equals("")) || (i.getId_unidade() == null) || (i.getMinimo() == null)) {
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
		li = new LI();
		gardarBusca = new ArrayList<Itens>();
	}

	public void mostrarLocais() {
		listLi = liDAO.listarLocalSemFalta(selc.getId());
	}

	public void completo() {
		gardarBusca = null;
		gardarBusca = new ArrayList<Itens>();
		PrimeFaces.current().ajax().update("formCorpo:tabela1");
		listI = null;
		listI = iDAO.listarTodos();
		PrimeFaces.current().ajax().update("formCorpo:tabela1");
	}

	public void orderBy() {
		gardarBusca = null;
		gardarBusca = new ArrayList<Itens>();
		PrimeFaces.current().ajax().update("formCorpo:tabela1");
		listI = null;
		listI = iDAO.listarItensFalta();
		PrimeFaces.current().ajax().update("formCorpo:tabela1");
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

	public List<LI> getListLi() {
		return listLi;
	}

	public void setListLi(List<LI> listLi) {
		this.listLi = listLi;
	}

	public FacesContext getContext() {
		return context;
	}

	public void setContext(FacesContext context) {
		this.context = context;
	}

	public List<Itens> getListI() {
		return listI;
	}

	public void setListI(List<Itens> listI) {
		this.listI = listI;
	}

	public List<Itens> getGardarBusca() {
		return gardarBusca;
	}

	public void setGardarBusca(List<Itens> gardarBusca) {
		this.gardarBusca = gardarBusca;
	}

}
