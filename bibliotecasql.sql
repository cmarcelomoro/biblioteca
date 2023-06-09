

CREATE TABLE pessoa ( 
	id INT Primary key auto_increment,
    nome VARCHAR(100),
    senha VARCHAR(100)
    );


CREATE TABLE livro (
  id INT primary key auto_increment,
  titulo VARCHAR(100),
  autor VARCHAR(100),
  ano INT,
  numPaginas INT,
  genero VARCHAR(50),
  classificacao VARCHAR(50)
);

create table doacao (
	id INT primary key auto_increment,
    fk_pessoa int,
    fk_livro int,
    
    foreign key(fk_pessoa)
    references pessoa (id),
    foreign key(fk_livro)
    references livro (id)
    );
    