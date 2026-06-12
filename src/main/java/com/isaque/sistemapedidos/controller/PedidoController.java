package com.isaque.sistemapedidos.controller;

import com.isaque.sistemapedidos.model.Pedido;
import com.isaque.sistemapedidos.service.PedidoService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PedidoController {
    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping("/pedidos")
    public void realizarPedido(@RequestBody Pedido pedido) {
        pedidoService.realizarPedido(pedido);
    }
}
