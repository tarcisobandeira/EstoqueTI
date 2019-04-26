package br.com.entities;

public class Funcionarios {

	private Integer id;
	private String nome;
	private Integer funcao;
	private String login;
	private String senha;
	
	public Funcionarios(Integer id, String nome, Integer funcao, String login, String senha) {
		super();
		this.id = id;
		this.nome = nome;
		this.funcao = funcao;
		this.login = login;
		this.senha = senha;
	}
	
	public Funcionarios() {
		
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getFuncao() {
		return funcao;
	}

	public void setFuncao(Integer funcao) {
		this.funcao = funcao;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
