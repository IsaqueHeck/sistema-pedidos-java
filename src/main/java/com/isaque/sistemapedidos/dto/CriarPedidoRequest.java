package com.isaque.sistemapedidos.dto;

import java.util.List;

public class CriarPedidoRequest {
    private String nomeCliente;
    private List<ItemPedidoRequest> itens;

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public List<ItemPedidoRequest> getItens() {
        return itens;
    }

    public void setItens(List<ItemPedidoRequest> itens) {
        this.itens = itens;
    }
}
