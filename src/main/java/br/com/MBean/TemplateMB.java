package br.com.MBean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class TemplateMB {

	public int opt = 1;

	public String mudar() {
		if (opt == 1) {
			return "/Template/listaItens.xhtml";
		} else if (opt == 2) {
			return "/Template/listaFuncionarios.xhtml";
		} else if (opt == 3) {
			return "/Template/listaLocalNu.xhtml";
		} else if (opt == 4) {
			return "/Template/listaLocalFaj.xhtml";
		} else if (opt == 5) {
			return "/Template/listaEntrada.xhtml";
		} else if (opt == 6) {
			return "/Template/listaSaida.xhtml";
		} else if (opt == 7) {
			return "/Template/listaTroca.xhtml";
		} else if (opt == 8) {
			return "/Template/listaEmprestimo.xhtml";
		} else if (opt == 9) {
			return "/Template/listaUnidade.xhtml";
		} else {
			return "/Template/listaGrafico.xhtml";
		}
	}

	public int getOpt() {
		return opt;
	}

	public void setOpt(int opt) {
		this.opt = opt;
	}

}
