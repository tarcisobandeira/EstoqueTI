package br.com.entities;

public class LI {

	private Integer id;
	private Integer id_itens;
	private Integer id_localizacao;
	private Integer estoque;
	private Localizacao localizacao;
	private Itens itens;

	public LI() {

	}

	public LI(Integer id, Integer id_itens, Integer id_localizacao, Integer estoque) {
		super();
		this.id = id;
		this.id_itens = id_itens;
		this.id_localizacao = id_localizacao;
		this.estoque = estoque;
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

	public Localizacao getLocalizacao() {
		return localizacao;
	}

	public void setLocalizacao(Localizacao localizacao) {
		this.localizacao = localizacao;
	}

	public Itens getItens() {
		return itens;
	}

	public void setItens(Itens itens) {
		this.itens = itens;
	}

	public Integer getEstoque() {
		return estoque;
	}

	public void setEstoque(Integer estoque) {
		this.estoque = estoque;
	}

}
