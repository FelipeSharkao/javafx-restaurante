package com.example.restaurante.models;

public class Prato {
    private Integer id;
    private String nome;
    private String ingredientes;
    private double preco;
    private String imagem;

    public Prato(String nome, String ingredientes, double preco, String imagem) {
        this.nome = nome;
        this.ingredientes = ingredientes;
        this.preco = preco;
        this.imagem = imagem;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(String ingredientes) {
        this.ingredientes = ingredientes;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }
}
