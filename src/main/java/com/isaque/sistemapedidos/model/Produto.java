package com.isaque.sistemapedidos.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Produto {
    @Id
    private int id;
    private String nome;
    private double preco;
    private int quantidadeEstoque;

    public Produto() {

    }

    public Produto(int id, String nome, double preco, int quantidadeEstoque) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }

    public int getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public void setQuantidadeEstoque(int quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }

    @Override
    public String toString() {
        return
                "\nID: " + id +
                        "\nNome: " + nome +
                        "\nPreço: R$" + preco +
                        "\nEstoque: " + quantidadeEstoque +
                        "\n";
    }
}
