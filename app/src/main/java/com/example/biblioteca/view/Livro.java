package com.example.biblioteca.view;

public class Livro {

    private String titulo;
    private String autor;
    private int ano;
    private int numPaginas;
    private String genero;
    private String classificacao;

    public Livro(String titulo, String autor, int ano, int numPaginas, String genero, String classificacao) {
        this.titulo = titulo;
        this.autor = autor;
        this.ano = ano;
        this.numPaginas = numPaginas;
        this.genero = genero;
        this.classificacao = classificacao;
    }
}
