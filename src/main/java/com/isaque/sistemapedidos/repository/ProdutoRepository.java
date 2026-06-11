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

    public Map<Integer, Produto> listarProdutos() {
        return produtos;
    }

   public void removerProduto(int id) {
        produtos.remove(id);
   }

   public void atualizarProduto(int id, Produto produtoAtualizado) {
        Produto produto = produtos.get(id);

        produto.setNome(produtoAtualizado.getNome());
        produto.setPreco(produtoAtualizado.getPreco());
        produto.setQuantidadeEstoque(produtoAtualizado.getQuantidadeEstoque());
   }

   public Map<Integer, Produto> getProdutos() {
        return produtos;
   }

}
