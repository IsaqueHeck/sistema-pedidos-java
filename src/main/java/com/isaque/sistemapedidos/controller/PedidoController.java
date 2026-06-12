package com.isaque.sistemapedidos.controller;

import com.isaque.sistemapedidos.model.Pedido;
import com.isaque.sistemapedidos.service.PedidoService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.service.annotation.PatchExchange;

import java.awt.*;
import java.util.List;

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

    @GetMapping("/pedidos")
    public List<Pedido> listarPedidos() {
        return pedidoService.listarPedidos();
    }

    @GetMapping("/pedidos/{id}")
    public Pedido buscarPorId(@PathVariable int id) {
        return pedidoService.buscarPorId(id);
    }
}
