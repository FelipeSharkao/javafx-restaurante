package com.example.restaurante.models;

public class Mesa {
    private Integer id;
    private int numero;
    private boolean ocupado = false;
    private boolean ativo = true;

    public Mesa(int numero) {
        this.numero = numero;
    }

    public Mesa(int numero, boolean ocupado) {
        this.numero = numero;
        this.ocupado = ocupado;
    }

    public Mesa(int numero, boolean ocupado, boolean ativo) {
        this.numero = numero;
        this.ocupado = ocupado;
        this.ativo = ativo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public boolean isOcupado() {
        return ocupado;
    }

    public void setOcupado(boolean ocupado) {
        this.ocupado = ocupado;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
}
