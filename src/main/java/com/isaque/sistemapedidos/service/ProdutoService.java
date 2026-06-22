package com.isaque.sistemapedidos.service;

import com.isaque.sistemapedidos.exceptions.EstoqueNegativoException;
import com.isaque.sistemapedidos.exceptions.PrecoNegativoException;
import com.isaque.sistemapedidos.exceptions.ProdutoNaoEncontradoException;
import com.isaque.sistemapedidos.model.Produto;
import com.isaque.sistemapedidos.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProdutoService {
  private final ProdutoRepository repository;

  public ProdutoService(ProdutoRepository repository) {
      this.repository = repository;
  }

  public void adicionarProduto(Produto produto) {

      if (produto.getPreco() < 0 ) {
          throw new IllegalArgumentException("Preço não pode ser negativo");
      }
      if(produto.getQuantidadeEstoque() < 0) {
          throw new IllegalArgumentException("Estoque não pode ser negativo");
      }

      repository.save(produto);
  }

  public Produto buscarPorId(Integer id) {
     Produto produto = repository.findById(id)
             .orElse(null);

     if(produto == null) {
         throw new ProdutoNaoEncontradoException("Produto inexistente");
     }
     return produto;
  }

    public List<Produto> listarProdutos() {
        return repository.findAll();
    }

    public void removerProduto(Integer id) {

        Produto produto = repository.findById(id)
                .orElse(null);

        if(produto == null) {
            throw new ProdutoNaoEncontradoException(
                    "Produto inexistente"
            );
        }

        repository.deleteById(id);
    }

    public void atualizarProduto(Integer id, Produto produtoAtualizado) {
      Produto produto = repository.findById(id)
              .orElse(null);

      if(produto == null) {
          throw new ProdutoNaoEncontradoException(
                  "Produto inexistente"
          );
      }

      if(produtoAtualizado.getPreco() < 0) {
          throw new PrecoNegativoException(
                  "O preço não deve ser negativo"
          );
      }

      if(produtoAtualizado.getQuantidadeEstoque() < 0) {
          throw new EstoqueNegativoException(
                  "A quantidade não deve ser negativa"
          );
      }
        produto.setNome(produtoAtualizado.getNome());

        produto.setPreco(produtoAtualizado.getPreco());

        produto.setQuantidadeEstoque(
                produtoAtualizado.getQuantidadeEstoque()
        );

        repository.save(produto);
    }

  public List<Produto> listarProdutosCaros() {
      return repository.findAll()
              .stream()
              .filter(produto -> produto.getPreco() > 100)
              .collect(Collectors.toList());
  }

  public List<Produto> listarProdutosSemEstoque() {
      return repository.findAll()
              .stream()
              .filter(produto -> produto.getQuantidadeEstoque() == 0)
              .collect(Collectors.toList());
  }

  public List<String> listarNomesProdutos() {
      return repository.findAll()
              .stream()
              .map(Produto::getNome)
              .collect(Collectors.toList());
  }

    public ProdutoRepository getRepository() {
      return repository;
    }
}
