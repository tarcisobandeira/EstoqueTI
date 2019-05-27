package br.com.MBean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.DAO.entradaDAO;
import br.com.DAO.funcionariosDAO;
import br.com.DAO.itensDAO;
import br.com.DAO.localizacaoDAO;
import br.com.DAO.saidaDAO;
import br.com.entities.Entrada;
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
	entradaDAO eDAO = new entradaDAO();
	Saida s = new Saida();
	Itens i = new Itens();
	Localizacao l = new Localizacao();
	Funcionarios f = new Funcionarios();
	List<Saida> listS = new ArrayList<Saida>();
	Integer codigo;
	Saida selc;

	public void fazerSaida() {
		if (testarCampos()) {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/yyyy");
			Calendar data = new GregorianCalendar();
			s.setDia(sdf.format(data.getTime()));

			if (s.getOS() == null) {
				s.setOS(0);
			}

			i = iDAO.buscarItem(s.getId_itens());
			i.setEstoque_at(i.getEstoque_at() - s.getSaida());

			if (sDAO.inserir(s)) {
				System.out.println("EstoqueTI:Foi feita a saída de " + i.getDescricao() + ".");
				if (iDAO.updateEstoque(i.getEstoque_at(), i.getId())) {
					System.out.println("EstoqueTI:Estoque atualizado.");
					zerar();
					zerarList();
					codigo = null;
				} else {
					System.out.println("EstoqueTI:Erro ao atualizar estoque.");
				}
			} else {
				System.out.println("EstoqueTI:Erro ao fazer a saída do item.");
			}
		} else {
			System.out.println("EstoqueTI:Campo vazio em saida single.");
		}

	}

	public void fazerMultSaida() {
		if (tCampos()) {
			for (Saida saida : listS) {
				i = iDAO.buscarItem(saida.getId_itens());
				i.setEstoque_at(i.getEstoque_at() - saida.getSaida());

				if (sDAO.inserir(saida)) {
					System.out.println("EstoqueTI:Foi feita a saída de " + saida.getItens().getDescricao() + ".");
					if (iDAO.updateEstoque(i.getEstoque_at(), i.getId())) {
						System.out.println("EstoqueTI:Estoque atualizado.");
					} else {
						System.out.println("EstoqueTI:Erro ao atualizar estoque.");
					}
				} else {
					System.out.println("EstoqueTI:Erro ao fazer a saída do item.");
				}
			}
			zerar();
			zerarList();
			codigo = null;
		} else {
			System.out.println("EstoqueTI:Algum item(ns) com informações vazias.");
		}
	}

	public void addListS() {
		Saida saida = new Saida();
		if (testarCampos()) {
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

			if (s.getOS() == null) {
				saida.setOS(0);
			} else {
				saida.setOS(s.getOS());
			}

			SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/yyyy");
			Calendar data = new GregorianCalendar();
			saida.setDia(sdf.format(data.getTime()));

			updateList();

			listS.add(saida);

			saida = new Saida();
			zerar();
		} else {
			System.out.println("EstoqueTI:Campo vazio em saida multipla.");
		}
	}

	public boolean testarCampos() {
		if ((s.getId_itens() == null) || (s.getId_localizacao() == null) || (s.getId_funcionario() == null)
				|| (s.getSaida() == null)) {
			return false;
		} else {
			return true;
		}
	}

	public boolean tCampos() {
		for (Saida saida : listS) {
			if ((saida.getId_itens() == null) || (saida.getId_localizacao() == null)
					|| (saida.getId_funcionario() == null) || (saida.getSaida() == null)) {
				return false;
			}
		}
		return true;
	}

	public boolean updateList() {
		int contador = 0;
		for (Saida saida : listS) {
			if (saida.equals(s)) {
				listS.remove(contador);
				return true;
			}
			contador++;
		}
		return false;
	}

	public boolean deletList() {
		int contador = 0;
		for (Saida saida : listS) {
			if (saida.equals(selc)) {
				listS.remove(contador);
				return true;
			}
			contador++;
		}
		return false;
	}

	public void buscar() {
		List<Entrada> ee = eDAO.buscarCodigo(codigo);

		for (Entrada entrada : ee) {

			Itens item = iDAO.buscarItem(entrada.getId_itens());

			Saida saida = new Saida();

			saida.setId_itens(item.getId());
			saida.setItens(item);

			listS.add(saida);
		}
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
		selc = null;
	}

	public void zerarList() {
		listS = new ArrayList<Saida>();
	}

	public void editar() {
		s = selc;
		i = iDAO.buscarItem(s.getId_itens());
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

	public entradaDAO geteDAO() {
		return eDAO;
	}

	public void seteDAO(entradaDAO eDAO) {
		this.eDAO = eDAO;
	}

	public Funcionarios getF() {
		return f;
	}

	public void setF(Funcionarios f) {
		this.f = f;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public Saida getSelc() {
		return selc;
	}

	public void setSelc(Saida selc) {
		this.selc = selc;
	}

}
