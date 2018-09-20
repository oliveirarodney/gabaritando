use master

alter database Gabaritando set single_user with rollback immediate
go

drop database Gabaritando
go

create database Gabaritando
go

use Gabaritando
go

CREATE TABLE Categoria
(
	codCategoria INT PRIMARY KEY IDENTITY (1,1),
	nomeCategoria VARCHAR(50)
)
						  
CREATE TABLE Usuario
(
	codUsuario INT PRIMARY KEY IDENTITY(1,1),
	nome VARCHAR(200) NOT NULL,
	userName VARCHAR(50) NOT NULL, 
	email varchar(50) NOT NULL,
	senha varchar(256) NOT NULL,
)

CREATE TABLE Quiz(
	codQuiz VARCHAR(8) PRIMARY KEY
)

CREATE TABLE Pergunta
(
	codPergunta INT PRIMARY KEY IDENTITY (1,1),
	descricao VARCHAR (500),
	codCategoria INT,
	FOREIGN KEY(codCategoria) REFERENCES Categoria(codCategoria)
)

CREATE TABLE Alternativa
(
	codAlternativa INT,	
	codPergunta INT,
	alternativa VARCHAR (200),
	situacao INT,--0 PARA ALTERNATIVAS FALSAS, 1 PARA VERDADEIRA
	CONSTRAINT PK_AlternativaPergunta PRIMARY KEY (codPergunta, codAlternativa),
	FOREIGN KEY (codPergunta) REFERENCES Pergunta(codPergunta)
)

CREATE TABLE PerguntasRespondidas
(
	codUsuario INT,
	codQuiz VARCHAR(8),
	codPergunta INT, 
	respostaUsuario INT,
	pontuacaoPergunta INT,
	PRIMARY KEY (codUsuario, codQuiz, codPergunta),
	FOREIGN KEY (codPergunta, respostaUsuario) REFERENCES Alternativa (codPergunta, codAlternativa),
	FOREIGN KEY (codQuiz) REFERENCES Quiz(codQuiz),
	FOREIGN KEY (codUsuario) REFERENCES Usuario(codUsuario)
)
