package com.isaque.sistemapedidos.service;

import com.isaque.sistemapedidos.exceptions.EstoqueInsuficienteException;
import com.isaque.sistemapedidos.exceptions.PedidoNaoEncontradoException;
import com.isaque.sistemapedidos.exceptions.ProdutoNaoEncontradoException;
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

    public PedidoService (PedidoRepository pedidoRepository, ProdutoRepository produtoRepository) {
        this.pedidoRepository = pedidoRepository;
        this.produtoRepository = produtoRepository;
    }

    public void realizarPedido(Pedido pedido) {
        double valorTotal = 0;

        for(Produto produtoPedido : pedido.getProdutos()) {
            Produto produtoEstoque = produtoRepository.findById(produtoPedido.getId())
                    .orElse(null);

            if(produtoEstoque == null) {
                throw new ProdutoNaoEncontradoException("Produto não encontrado");
            }

            if(produtoEstoque.getQuantidadeEstoque() < produtoPedido.getQuantidadeEstoque()) {
                throw new EstoqueInsuficienteException("Estoque insuficiente para: " + produtoEstoque.getNome());
            }

            produtoEstoque.setQuantidadeEstoque(produtoEstoque.getQuantidadeEstoque() - produtoPedido.getQuantidadeEstoque());

            valorTotal += produtoEstoque.getPreco() * produtoPedido.getQuantidadeEstoque();
        }
        pedido.setValorTotal(valorTotal);
        pedidoRepository.salvarPedido(pedido);
    }

    public Pedido buscarPorId(int id) {

        Pedido pedido = pedidoRepository.buscarPorId(id);

        if(pedido == null) {
            throw new PedidoNaoEncontradoException(
                    "Pedido não encontrado"
            );
        }

        return pedido;
    }



    public List<Pedido> listarPedidos() {
        return pedidoRepository.listarPedidos()
                .values()
                .stream()
                .toList();
    }

    public List<Pedido> listarPedidosAcimaDeValor(double valor) {
        return pedidoRepository.getPedidos()
                .values()
                .stream()
                .filter(pedido -> pedido.getValorTotal() > valor)
                .collect(Collectors.toList());
    }
}
