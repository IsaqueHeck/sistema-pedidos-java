package com.isaque.sistemapedidos.controller;

import com.isaque.sistemapedidos.model.Produto;
import com.isaque.sistemapedidos.service.ProdutoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProdutoController {
    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }


    @PostMapping("/produtos")
    public void adicionarProduto(@RequestBody Produto produto) {
      produtoService.adicionarProduto(produto);
    }

    @GetMapping("/produtos")
    public List<Produto> listarProdutos() {
        return produtoService.listarProdutos();
    }

   @GetMapping("/produtos/{id}")
    public Produto buscarPorId(@PathVariable int id) {
        return produtoService.buscarPorId(id);
   }

   @DeleteMapping("/produtos/{id}")
   public void removerProduto(@PathVariable int id) {
        produtoService.removerProduto(id);
   }


}
