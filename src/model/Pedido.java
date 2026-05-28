package model;

import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private int id;
    private String nomeCliente;
    private List<Produto> produtos;
    private double valorTotal;

    public Pedido(int id, String nomeCliente, double valorTotal) {
        this.id = id;
        this.nomeCliente = nomeCliente;
        this.valorTotal = valorTotal;
        produtos = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public void adicionarProduto(Produto produto) {
        produtos.add(produto);
    }

    @Override
    public String toString() {
        return
                "\nPedido ID: " + id +
                        "\nCliente: " + nomeCliente +
                        "\nProdutos: " + produtos +
                        "\nValor Total: R$" + valorTotal +
                        "\n";
    }
}
