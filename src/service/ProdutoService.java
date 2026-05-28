package service;

import exceptions.ProdutoDuplicadoException;
import exceptions.ProdutoNaoEncontradoException;
import model.Produto;
import repository.ProdutoRepository;

import java.util.List;
import java.util.stream.Collectors;

public class ProdutoService {
  private ProdutoRepository repository;

  public ProdutoService() {
      this.repository = new ProdutoRepository();
  }

  public void adicionarProduto(Produto produto) {
      Produto produtoExistente = repository.buscarPorId(produto.getId());

      if (produtoExistente != null) {
          throw new ProdutoDuplicadoException("Já existe produto com id: " + produto.getId());
      }
      if (produto.getPreco() < 0 ) {
          throw new IllegalArgumentException("Preço não pode ser negativo");
      }
      if(produto.getQuantidadeEstoque() < 0) {
          throw new IllegalArgumentException("Estoque não pode ser negativo");
      }

      repository.salvarProduto(produto);
  }

  public Produto buscarPorId(int id) {
     Produto produto = repository.buscarPorId(id);
     if(produto == null) {
         throw new ProdutoNaoEncontradoException("Produto inexistente");
     }
     return produto;
  }

  public void listarProdutos() {
      repository.listarProdutos();
  }

  public void removerProduto(int id) {
      repository.removerProduto(id);
  }

  public List<Produto> listarProdutosCaros() {
      return repository.getProdutos()
              .values()
              .stream()
              .filter(produto -> produto.getPreco() > 100)
              .collect(Collectors.toList());
  }

  public List<Produto> listarProdutosSemEstoque() {
      return repository.getProdutos()
              .values()
              .stream()
              .filter(produto -> produto.getQuantidadeEstoque() == 0)
              .collect(Collectors.toList());
  }

  public List<String> listarNomesProdutos() {
      return repository.getProdutos()
              .values()
              .stream()
              .map(Produto::getNome)
              .collect(Collectors.toList());
  }

    public ProdutoRepository getRepository() {
      return repository;
    }
}
