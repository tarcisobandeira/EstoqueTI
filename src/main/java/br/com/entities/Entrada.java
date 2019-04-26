package br.com.entities;

public class Entrada {

	private Integer id;
	private Integer codigo;
	private Integer id_itens;
	private Integer id_localizacao;
	private Integer entrada;
	private String dia;
	private Itens itens;
	private Localizacao localizacao;
	
	public Entrada(Integer id, Integer codigo, Integer id_itens, Integer id_localizacao, Integer entrada, String dia) {
		super();
		this.id = id;
		this.codigo = codigo;
		this.id_itens = id_itens;
		this.id_localizacao = id_localizacao;
		this.entrada = entrada;
		this.dia = dia;
	}
	
	public Entrada() {
		// TODO Auto-generated constructor stub
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId_itens() {
		return id_itens;
	}

	public void setId_itens(Integer id_itens) {
		this.id_itens = id_itens;
	}

	public Integer getId_localizacao() {
		return id_localizacao;
	}

	public void setId_localizacao(Integer id_localizacao) {
		this.id_localizacao = id_localizacao;
	}

	public Integer getEntrada() {
		return entrada;
	}

	public void setEntrada(Integer entrada) {
		this.entrada = entrada;
	}

	public String getDia() {
		return dia;
	}

	public void setDia(String dia) {
		this.dia = dia;
	}

	public Itens getItens() {
		return itens;
	}

	public void setItens(Itens itens) {
		this.itens = itens;
	}

	public Localizacao getLocalizacao() {
		return localizacao;
	}

	public void setLocalizacao(Localizacao localizacao) {
		this.localizacao = localizacao;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

}
