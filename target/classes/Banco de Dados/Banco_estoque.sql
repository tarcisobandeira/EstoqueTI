CREATE DATABASE estoque;

USE estoque;

CREATE TABLE Localizacao(
	id 			INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
	local_nome	VARCHAR(50),
	localNF		INT
);

CREATE TABLE Itens(
	id 			INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
	descricao 	VARCHAR(500),
	unidade		INT,
	minimo		INT,
	saldo_ini	INT,
	estoque_at	INT,
	id_localizacao INT,
	FOREIGN KEY (id_localizacao) REFERENCES Localizacao (id)
);

CREATE TABLE Funcionarios(
	id 			INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
	nome		VARCHAR(100),
	funcao		INT,
	login		VARCHAR(50),
	senha		VARCHAR(50)
);

CREATE TABLE Entrada(
	id			INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
	codigo		INT,
	id_itens	INT,
	id_localizacao INT,
	entrada		INT,
	dia			VARCHAR(12),
	FOREIGN KEY (id_itens) REFERENCES Itens(id),
	FOREIGN KEY (id_localizacao) REFERENCES Localizacao (id)
);

CREATE TABLE Saida(
	id			INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
	id_itens	INT,
	id_localizacao INT,
	id_funcionario INT,
	saida		INT,
	dia			VARCHAR(12),
	OS  		INT,
	FOREIGN KEY (id_itens) REFERENCES Itens(id),
	FOREIGN KEY (id_localizacao) REFERENCES Localizacao (id),
	FOREIGN KEY (id_funcionario) REFERENCES Funcionarios (id)
);

INSERT INTO Funcionarios(nome,funcao,login,senha)
		VALUES	("Tarciso", 3, "tarciso", 123)
