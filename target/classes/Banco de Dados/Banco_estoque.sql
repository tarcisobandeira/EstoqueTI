CREATE DATABASE estoqueTi;

USE estoqueTi;

CREATE TABLE Unidade(
	id			INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
	unidade		VARCHAR(50)
);

CREATE TABLE Localizacao(
	id 			INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
	local_nome	VARCHAR(50),
	localNF		INT
);

CREATE TABLE Itens(
	id 			INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
	descricao 	VARCHAR(500),
	id_unidade	INT,
	minimo		INT,
	estoque_at	INT,
	FOREIGN KEY (id_unidade) REFERENCES Unidade (id)
);

CREATE TABLE LI(
	id			INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
	id_localizacao	INT,
	id_itens	INT,
	estoque		INT,
	FOREIGN KEY (id_localizacao) REFERENCES Localizacao (id),
	FOREIGN KEY (id_itens) REFERENCES Itens (id)
);

CREATE TABLE Funcionarios(
	id 			INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
	nome		VARCHAR(100),
	funcao		INT,
	login		VARCHAR(50),
	senha		VARCHAR(50)
);

CREATE TABLE Troca(
	id			INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
	id_itens	INT,
	id_localAn	INT,
	id_localAt	INT,
	quantidade	INT,
	dia			VARCHAR(12),
	FOREIGN KEY (id_itens) REFERENCES Itens(id),
	FOREIGN KEY (id_localAn) REFERENCES Localizacao(id),
	FOREIGN KEY (id_localAt) REFERENCES Localizacao(id)
);

CREATE TABLE Emprestimos(
	id			INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
	dia_saida	VARCHAR(12),
	dia_devol	VARCHAR(12),
	colaborador VARCHAR(30),
	id_itens	INT,
	id_localizacao INT,
	quantidade	INT,
	obs			VARCHAR(500),
	limite		INT,
	FOREIGN KEY (id_itens) REFERENCES Itens(id),
	FOREIGN KEY (id_localizacao) REFERENCES Localizacao(id)
);

CREATE TABLE Entrada(
	id			INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
	codigo		INT,
	id_itens	INT,
	id_localizacao INT,
	entrada		INT,
	dia			VARCHAR(12),
	FOREIGN KEY (id_itens) REFERENCES Itens(id),
	FOREIGN KEY (id_localizacao) REFERENCES Localizacao(id)
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
	FOREIGN KEY (id_localizacao) REFERENCES Localizacao(id),
	FOREIGN KEY (id_funcionario) REFERENCES Funcionarios(id)
);

INSERT INTO `funcionarios` (`id`, `nome`, `funcao`, `login`, `senha`) VALUES
(1, 'Tarciso', 3, 'tarciso', '123'),
(2, 'Rian', 1, '956', '123'),
(3, 'Léo', 2, '165', '123'),
(4, 'Nailton', 2, '999', '123'),
(5, 'Gustavo', 1, 'gpinheiro', '123'),
(6, 'Gelado', 1, 'Geladao', '123'),
(7, 'Henrique', 2, 'hvgodoy', 'cloud157'),
(8, 'closdovinson', 2, '\'_;\\{[]}º°', '1234567'),
(9, 'Jailoson', 1, 'já', '123');

INSERT INTO `localizacao` (`id`, `local_nome`, `localNF`) VALUES
(1, 'Armário 1 ', 1),
(2, 'Armário 2', 1),
(3, 'CA', 2),
(4, 'HEV', 2),
(5, 'Armário 3', 1),
(6, 'CSC', 2),
(7, 'LAB 8', 2);