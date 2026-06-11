package com.isaque.sistemapedidos.controller;

import com.isaque.sistemapedidos.service.ProdutoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProdutoController {
    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping("/teste")
    public String teste() {
        return "API de Produtos funcionando!";
    }
}
