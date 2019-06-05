package br.com.entities;

public class Troca {

	private Integer id;
	private Integer id_itens;
	private Integer id_localAn;
	private Integer id_localAt;
	private Integer quantidade;
	private String dia;
	private Itens itens;
	private Localizacao LAN;
	private Localizacao LAT;

	public Troca(Integer id, Integer id_itens, Integer id_localAn, Integer id_localAt, Integer quantidade, String dia) {
		super();
		this.id = id;
		this.id_itens = id_itens;
		this.id_localAn = id_localAn;
		this.id_localAt = id_localAt;
		this.quantidade = quantidade;
		this.dia = dia;
	}

	public Troca() {

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

	public Integer getId_localAn() {
		return id_localAn;
	}

	public void setId_localAn(Integer id_localAn) {
		this.id_localAn = id_localAn;
	}

	public Integer getId_localAt() {
		return id_localAt;
	}

	public void setId_localAt(Integer id_localAt) {
		this.id_localAt = id_localAt;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
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

	public Localizacao getLAN() {
		return LAN;
	}

	public void setLAN(Localizacao lAN) {
		LAN = lAN;
	}

	public Localizacao getLAT() {
		return LAT;
	}

	public void setLAT(Localizacao lAT) {
		LAT = lAT;
	}

}
