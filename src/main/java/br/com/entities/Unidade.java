package br.com.entities;

public class Unidade {
	
	private Integer id;
	private String unidade;
	
	public Unidade() {
		// TODO Auto-generated constructor stub
	}
	
	public Unidade(Integer id, String unidade) {
		super();
		this.id = id;
		this.unidade = unidade;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUnidade() {
		return unidade;
	}
	public void setUnidade(String unidade) {
		this.unidade = unidade;
	}

}
