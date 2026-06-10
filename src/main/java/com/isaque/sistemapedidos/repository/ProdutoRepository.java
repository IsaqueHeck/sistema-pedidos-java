package com.isaque.sistemapedidos.repository;

import com.isaque.sistemapedidos.model.Produto;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class ProdutoRepository {
   private Map<Integer, Produto> produtos;

    public ProdutoRepository() {
        produtos = new HashMap<>();
    }

    public void salvarProduto(Produto produto) {
        produtos.put(produto.getId(), produto);
    }

    public Produto buscarPorId(int id) {
      return produtos.get(id);
    }

    public void listarProdutos() {
        for(Produto produto : produtos.values()) {
            System.out.println(produto);
        }
    }

   public void removerProduto(int id) {
        produtos.remove(id);
   }

   public Map<Integer, Produto> getProdutos() {
        return produtos;
   }

}
