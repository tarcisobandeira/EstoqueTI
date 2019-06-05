package br.com.MBean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.DAO.liDAO;
import br.com.DAO.trocaDAO;
import br.com.entities.LI;
import br.com.entities.Troca;

@ManagedBean
@ViewScoped
public class TrocaEstoqueMB {

	Troca t = new Troca();

	liDAO liDAO = new liDAO();
	trocaDAO tDAO = new trocaDAO();

	List<LI> listLi = new ArrayList<LI>();

	public void fazerMovimento() {
		if (testarCampos()) {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/yyyy");
			Calendar data = new GregorianCalendar();
			t.setDia(sdf.format(data.getTime()));
			
			if(tDAO.inserir(t)) {
				if(liDAO.inserirTroca(t)) {
					System.out.println("EstoqueTI:Troca de local completa.");
					zerar();
				}else {
					System.out.println("EstoqueTI:Erro ao fazer a ligação com o novo local.");
				}
			}else {
				System.out.println("EstoqueTI:Erro ao fazer a troca no banco.");
			}
			
		}else {
			System.out.println("EstoqueTI:Campo vazio em troca de estoque.");
		}
	}

	public void listarLocal() {
		listLi = new ArrayList<LI>();
		listLi = liDAO.listarLocal(t.getId_itens());
	}

	public boolean testarCampos() {
		if (t.getId_itens() == null || t.getId_localAn() == null || t.getId_localAt() == null
				|| t.getQuantidade() == null) {
			return false;
		} else {
			return true;
		}
	}
	
	public void zerar(){
		t = new Troca();
		listLi = new ArrayList<LI>();
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
}
