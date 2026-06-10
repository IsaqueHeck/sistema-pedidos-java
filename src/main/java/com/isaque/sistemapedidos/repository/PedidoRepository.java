package com.isaque.sistemapedidos.repository;

import com.isaque.sistemapedidos.model.Pedido;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class PedidoRepository {
    private Map<Integer, Pedido> pedidos;

    public PedidoRepository() {
        pedidos = new HashMap<>();
    }

    public void salvarPedido(Pedido pedido) {
        pedidos.put(pedido.getId(), pedido);
    }

    public Pedido buscarPorId(int id) {
        return pedidos.get(id);
    }

    public void listarPedidos() {
        for(Pedido pedido : pedidos.values()) {
            System.out.println(pedido);
        }
    }

    public Map<Integer, Pedido> getPedidos() {
        return pedidos;
    }
}
