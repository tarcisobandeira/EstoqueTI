package br.com.entities;

public class Emprestimo {

	private Integer id;
	private String dia_saida;
	private String dia_devol;
	private String colaborador;
	private Integer id_itens;
	private Integer id_localizacao;
	private Integer quantidade;
	private String OBS;
	private Integer limite;
	private Itens itens;
	private Localizacao localizacao;

	public Emprestimo(Integer id, String dia_saida, String dia_devol, String colaborador, Integer id_itens,
			Integer id_localizacao, Integer quantidade, String OBS, Integer limite) {
		super();
		this.id = id;
		this.dia_saida = dia_saida;
		this.dia_devol = dia_devol;
		this.colaborador = colaborador;
		this.id_itens = id_itens;
		this.id_localizacao = id_localizacao;
		this.quantidade = quantidade;
		this.OBS = OBS;
		this.limite = limite;
	}

	public Emprestimo() {

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDia_saida() {
		return dia_saida;
	}

	public void setDia_saida(String dia_saida) {
		this.dia_saida = dia_saida;
	}

	public String getDia_devol() {
		return dia_devol;
	}

	public void setDia_devol(String dia_devol) {
		this.dia_devol = dia_devol;
	}

	public String getColaborador() {
		return colaborador;
	}

	public void setColaborador(String colaborador) {
		this.colaborador = colaborador;
	}

	public Integer getId_itens() {
		return id_itens;
	}

	public void setId_itens(Integer id_itens) {
		this.id_itens = id_itens;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public String getOBS() {
		return OBS;
	}

	public void setOBS(String oBS) {
		OBS = oBS;
	}

	public Itens getItens() {
		return itens;
	}

	public void setItens(Itens itens) {
		this.itens = itens;
	}

	public Integer getLimite() {
		return limite;
	}

	public void setLimite(Integer limite) {
		this.limite = limite;
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
