package br.com.entities;

public class Localizacao {
	private Integer id;
	private String local_nome;
	private Integer localNF;

	public Localizacao(Integer id, String local_nome, Integer localNF) {
		super();
		this.id = id;
		this.local_nome = local_nome;
		this.localNF = localNF;
	}

	public Localizacao() {
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLocal_nome() {
		return local_nome;
	}

	public void setLocal_nome(String local_nome) {
		this.local_nome = local_nome;
	}

	public Integer getLocalNF() {
		return localNF;
	}

	public void setLocalNF(Integer localNF) {
		this.localNF = localNF;
	}
}
