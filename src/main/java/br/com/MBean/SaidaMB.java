package br.com.MBean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.DAO.funcionariosDAO;
import br.com.DAO.itensDAO;
import br.com.DAO.localizacaoDAO;
import br.com.DAO.saidaDAO;
import br.com.entities.Funcionarios;
import br.com.entities.Itens;
import br.com.entities.Localizacao;
import br.com.entities.Saida;

@ManagedBean
@ViewScoped
public class SaidaMB {

	saidaDAO sDAO = new saidaDAO();
	itensDAO iDAO = new itensDAO();
	localizacaoDAO lDAO = new localizacaoDAO();
	funcionariosDAO fDAO = new funcionariosDAO();
	Saida s = new Saida();
	Itens i = new Itens();
	Localizacao l = new Localizacao();
	Funcionarios f = new Funcionarios();
	List<Saida> listS = new ArrayList<Saida>();

	public void fazerSaida() {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/yyyy");
		Calendar data = new GregorianCalendar();
		s.setDia(sdf.format(data.getTime()));

		i = iDAO.buscarItem(s.getId_itens());
		i.setEstoque_at(i.getEstoque_at() - s.getSaida());

		if (sDAO.inserir(s)) {
			System.out.println("EstoqueTI:Foi feita a sa�da de " + i.getDescricao() + ".");
			if (iDAO.updateEstoque(i.getEstoque_at(), i.getId())) {
				System.out.println("EstoqueTI:Estoque atualizado.");
				zerar();
				zerarList();
			} else {
				System.out.println("EstoqueTI:Erro ao atualizar estoque.");
			}
		} else {
			System.out.println("EstoqueTI:Erro ao fazer a sa�da do item.");
		}

	}

	public void fazerMultSaida() {
		for (Saida saida : listS) {
			i = iDAO.buscarItem(saida.getId_itens());
			i.setEstoque_at(i.getEstoque_at() - saida.getSaida());

			if (sDAO.inserir(saida)) {
				System.out.println("EstoqueTI:Foi feita a sa�da de " + saida.getItens().getDescricao() + ".");
				if (iDAO.updateEstoque(i.getEstoque_at(), i.getId())) {
					System.out.println("EstoqueTI:Estoque atualizado.");
				} else {
					System.out.println("EstoqueTI:Erro ao atualizar estoque.");
				}
			} else {
				System.out.println("EstoqueTI:Erro ao fazer a sa�da do item.");
			}
		}
		zerar();
		zerarList();
	}

	public void addListS() {
		Saida saida = new Saida();
		
		i = iDAO.buscarItem(s.getId_itens());
		l = lDAO.buscarLocal(s.getId_localizacao());
		f = fDAO.buscarFuncionarioId(s.getId_funcionario());

		saida.setItens(i);
		saida.setLocalizacao(l);
		saida.setFuncionarios(f);
		saida.setSaida(s.getSaida());
		saida.setId_itens(s.getId_itens());
		saida.setId_localizacao(s.getId_localizacao());
		saida.setId_funcionario(s.getId_funcionario());

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/yyyy");
		Calendar data = new GregorianCalendar();
		saida.setDia(sdf.format(data.getTime()));

		listS.add(saida);

		saida = new Saida();
		zerar();
	}

	public void listarTotal() {
		int id = s.getId_itens();
		i = iDAO.buscarItem(id);
	}

	public void zerar() {
		s = new Saida();
		i = new Itens();
		l = new Localizacao();
		f = new Funcionarios();
	}

	public void zerarList() {
		listS = new ArrayList<Saida>();
	}

	public saidaDAO getsDAO() {
		return sDAO;
	}

	public void setsDAO(saidaDAO sDAO) {
		this.sDAO = sDAO;
	}

	public Saida getS() {
		return s;
	}

	public void setS(Saida s) {
		this.s = s;
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

	public List<Saida> getListS() {
		return listS;
	}

	public void setListS(List<Saida> listS) {
		this.listS = listS;
	}

	public localizacaoDAO getlDAO() {
		return lDAO;
	}

	public void setlDAO(localizacaoDAO lDAO) {
		this.lDAO = lDAO;
	}

	public funcionariosDAO getfDAO() {
		return fDAO;
	}

	public void setfDAO(funcionariosDAO fDAO) {
		this.fDAO = fDAO;
	}

	public Localizacao getL() {
		return l;
	}

	public void setL(Localizacao l) {
		this.l = l;
	}

	public Funcionarios getF() {
		return f;
	}

	public void setF(Funcionarios f) {
		this.f = f;
	}

}