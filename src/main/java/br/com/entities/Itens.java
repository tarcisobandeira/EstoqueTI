package br.com.entities;

public class Itens {
	private Integer id;
	private String descricao;
	private Integer unidade;
	private Integer minimo;
	private Integer saldo_ini;
	private Integer estoque_at;
	private Integer id_localizacao;
	private Localizacao localizacao;
	
	public Itens(Integer id, String descricao, Integer unidade, Integer minimo, Integer saldo_ini, Integer estoque_at, Integer id_localizacao) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.unidade = unidade;
		this.minimo = minimo;
		this.saldo_ini = saldo_ini;
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

	public Integer getUnidade() {
		return unidade;
	}

	public void setUnidade(Integer unidade) {
		this.unidade = unidade;
	}

	public Integer getMinimo() {
		return minimo;
	}

	public void setMinimo(Integer minimo) {
		this.minimo = minimo;
	}

	public Integer getSaldo_ini() {
		return saldo_ini;
	}

	public void setSaldo_ini(Integer saldo_ini) {
		this.saldo_ini = saldo_ini;
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

}
