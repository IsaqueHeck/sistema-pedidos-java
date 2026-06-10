package com.isaque.sistemapedidos.service;

import com.isaque.sistemapedidos.exceptions.EstoqueInsuficienteException;
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
    private PedidoRepository pedidoRepository;
    private ProdutoRepository produtoRepository;

    public PedidoService (ProdutoRepository produtoRepository) {
        this.pedidoRepository = new PedidoRepository();
        this.produtoRepository = produtoRepository;
    }

    public void realizarPedido(Pedido pedido) {
        double valorTotal = 0;

        for(Produto produtoPedido : pedido.getProdutos()) {
            Produto produtoEstoque = produtoRepository.buscarPorId(produtoPedido.getId());

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

    public List<Pedido> listarPedidosAcimaDeValor(double valor) {
        return pedidoRepository.getPedidos()
                .values()
                .stream()
                .filter(pedido -> pedido.getValorTotal() > valor)
                .collect(Collectors.toList());
    }
}
