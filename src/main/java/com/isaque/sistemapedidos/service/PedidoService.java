package com.isaque.sistemapedidos.service;

import com.isaque.sistemapedidos.exceptions.EstoqueInsuficienteException;
import com.isaque.sistemapedidos.exceptions.PedidoNaoEncontradoException;
import com.isaque.sistemapedidos.exceptions.ProdutoNaoEncontradoException;
import com.isaque.sistemapedidos.model.ItemPedido;
import com.isaque.sistemapedidos.model.Pedido;
import com.isaque.sistemapedidos.model.Produto;
import com.isaque.sistemapedidos.repository.PedidoRepository;
import com.isaque.sistemapedidos.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PedidoService {
    private final PedidoRepository pedidoRepository;
    private final ProdutoRepository produtoRepository;

    public PedidoService(PedidoRepository pedidoRepository, ProdutoRepository produtoRepository) {
        this.pedidoRepository = pedidoRepository;
        this.produtoRepository = produtoRepository;
    }

    public void realizarPedido(Pedido pedido) {
        double valorTotal = 0;

        for (ItemPedido item : pedido.getItens()) {
            Produto produtoEstoque = produtoRepository.findById(item.getProduto().getId())
                    .orElse(null);

            if (produtoEstoque == null) {
                throw new ProdutoNaoEncontradoException("Produto não encontrado");
            }

            if (produtoEstoque.getQuantidadeEstoque() < item.getQuantidade()) {
                throw new EstoqueInsuficienteException("Estoque insuficiente para: " + produtoEstoque.getNome());
            }

            produtoEstoque.setQuantidadeEstoque(produtoEstoque.getQuantidadeEstoque() - item.getQuantidade());

            produtoRepository.save(produtoEstoque);

            item.setProduto(produtoEstoque);
            item.setPedido(pedido);

            valorTotal += produtoEstoque.getPreco() * item.getQuantidade();
        }
        pedido.setValorTotal(valorTotal);
        pedidoRepository.save(pedido);
    }

    public Pedido buscarPorId(Integer id) {

        Pedido pedido = pedidoRepository.findById(id)
                .orElse(null);

        if (pedido == null) {
            throw new PedidoNaoEncontradoException(
                    "Pedido não encontrado"
            );
        }

        return pedido;
    }


    public List<Pedido> listarPedidos() {
        return pedidoRepository.findAll();
    }

    public List<Pedido> listarPedidosAcimaDeValor(double valor) {
        return pedidoRepository.findAll()
                .stream()
                .filter(pedido -> pedido.getValorTotal() > valor)
                .collect(Collectors.toList());
    }
}