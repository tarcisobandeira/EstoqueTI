package br.com.entities;

public class Itens {
	private Integer id;
	private String descricao;
	private Integer id_unidade;
	private Integer minimo;
	private Integer estoque_at;
	private Unidade unidade;

	public Itens(Integer id, String descricao, Integer id_unidade, Integer minimo, Integer estoque_at) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.id_unidade = id_unidade;
		this.minimo = minimo;
		this.estoque_at = estoque_at;
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

	public Unidade getUnidade() {
		return unidade;
	}

	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
	}

}
