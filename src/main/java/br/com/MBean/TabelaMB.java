
package br.com.MBean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.DAO.entradaDAO;
import br.com.DAO.funcionariosDAO;
import br.com.DAO.itensDAO;
import br.com.DAO.localizacaoDAO;
import br.com.DAO.saidaDAO;
import br.com.DAO.trocaDAO;
import br.com.DAO.unidadeDAO;
import br.com.entities.Entrada;
import br.com.entities.Funcionarios;
import br.com.entities.Itens;
import br.com.entities.Localizacao;
import br.com.entities.Saida;
import br.com.entities.Troca;
import br.com.entities.Unidade;

@ManagedBean
@ViewScoped
public class TabelaMB {

	itensDAO iDAO = new itensDAO();
	localizacaoDAO lDAO = new localizacaoDAO();
	funcionariosDAO fDAO = new funcionariosDAO();
	entradaDAO eDAO = new entradaDAO();
	saidaDAO sDAO = new saidaDAO();
	unidadeDAO uDAO = new unidadeDAO();
	trocaDAO tDAO = new trocaDAO();
	
	List<Itens> itensL = new ArrayList<Itens>();
	List<Localizacao> localnL = new ArrayList<Localizacao>();
	List<Localizacao> localfL = new ArrayList<Localizacao>();
	List<Funcionarios> funciL = new ArrayList<Funcionarios>();
	List<Entrada> entradaL = new ArrayList<Entrada>();
	List<Saida> saidaL = new ArrayList<Saida>();
	List<Unidade> unidadeL = new ArrayList<Unidade>();
	List<Troca> trocaL = new ArrayList<Troca>();
	
	int opt;

	public TabelaMB() {
		listar();
	}

	public void listar() {
		itensL = iDAO.listarTodos();
		localnL = lDAO.listarTodosN();
		localfL = lDAO.listarTodosF();
		funciL = fDAO.listarTodos();
		entradaL = eDAO.listarTodos();
		saidaL = sDAO.listarTodos();
		unidadeL = uDAO.listarTodos();
		trocaL = tDAO.listarTodos();
	}

	public void orderBy() {
		itensL = null;
		itensL = iDAO.listarItensFalta();
	}

	public itensDAO getiDAO() {
		return iDAO;
	}

	public void setiDAO(itensDAO iDAO) {
		this.iDAO = iDAO;
	}

	public List<Itens> getItensL() {
		return itensL;
	}

	public void setItensL(List<Itens> itensL) {
		this.itensL = itensL;
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

	public List<Localizacao> getLocalnL() {
		return localnL;
	}

	public void setLocalnL(List<Localizacao> localnL) {
		this.localnL = localnL;
	}

	public List<Localizacao> getLocalfL() {
		return localfL;
	}

	public void setLocalfL(List<Localizacao> localfL) {
		this.localfL = localfL;
	}

	public List<Funcionarios> getFunciL() {
		return funciL;
	}

	public void setFunciL(List<Funcionarios> funciL) {
		this.funciL = funciL;
	}

	public entradaDAO geteDAO() {
		return eDAO;
	}

	public void seteDAO(entradaDAO eDAO) {
		this.eDAO = eDAO;
	}

	public saidaDAO getsDAO() {
		return sDAO;
	}

	public void setsDAO(saidaDAO sDAO) {
		this.sDAO = sDAO;
	}

	public List<Entrada> getEntradaL() {
		return entradaL;
	}

	public void setEntradaL(List<Entrada> entradaL) {
		this.entradaL = entradaL;
	}

	public List<Saida> getSaidaL() {
		return saidaL;
	}

	public void setSaidaL(List<Saida> saidaL) {
		this.saidaL = saidaL;
	}

	public unidadeDAO getuDAO() {
		return uDAO;
	}

	public void setuDAO(unidadeDAO uDAO) {
		this.uDAO = uDAO;
	}

	public List<Unidade> getUnidadeL() {
		return unidadeL;
	}

	public void setUnidadeL(List<Unidade> unidadeL) {
		this.unidadeL = unidadeL;
	}

	public int getOpt() {
		return opt;
	}

	public void setOpt(int opt) {
		this.opt = opt;
	}

	public trocaDAO gettDAO() {
		return tDAO;
	}

	public void settDAO(trocaDAO tDAO) {
		this.tDAO = tDAO;
	}

	public List<Troca> getTrocaL() {
		return trocaL;
	}

	public void setTrocaL(List<Troca> trocaL) {
		this.trocaL = trocaL;
	}

}
