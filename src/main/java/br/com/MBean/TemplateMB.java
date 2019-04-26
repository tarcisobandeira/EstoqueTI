package br.com.MBean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
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
		} else {
			return "/Template/listaSaida.xhtml";
		}
	}

	public int getOpt() {
		return opt;
	}

	public void setOpt(int opt) {
		this.opt = opt;
	}

}
