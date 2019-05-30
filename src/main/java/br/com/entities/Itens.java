package br.com.entities;

public class Itens {
	private Integer id;
	private String descricao;
	private Integer id_unidade;
	private Integer minimo;
	private Integer estoque_at;
	private Integer id_localizacao;
	private Localizacao localizacao;
	private Unidade unidade;

	public Itens(Integer id, String descricao, Integer id_unidade, Integer minimo, Integer estoque_at,
			Integer id_localizacao) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.id_unidade = id_unidade;
		this.minimo = minimo;
		this.estoque_at = estoque_at;
		this.id_localizacao = id_localizacao;
	}

	public Itens() {

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getId_unidade() {
		return id_unidade;
	}

	public void setId_unidade(Integer id_unidade) {
		this.id_unidade = id_unidade;
	}

	public Integer getMinimo() {
		return minimo;
	}

	public void setMinimo(Integer minimo) {
		this.minimo = minimo;
	}

	public Integer getEstoque_at() {
		return estoque_at;
	}

	public void setEstoque_at(Integer estoque_at) {
		this.estoque_at = estoque_at;
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

	public Unidade getUnidade() {
		return unidade;
	}

	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
	}

}
