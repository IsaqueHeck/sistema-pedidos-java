package com.isaque.sistemapedidos.service;

import com.isaque.sistemapedidos.dto.CriarPedidoRequest;
import com.isaque.sistemapedidos.dto.ItemPedidoRequest;
import com.isaque.sistemapedidos.exceptions.EstoqueInsuficienteException;
import com.isaque.sistemapedidos.exceptions.PedidoNaoEncontradoException;
import com.isaque.sistemapedidos.exceptions.ProdutoNaoEncontradoException;
import com.isaque.sistemapedidos.model.ItemPedido;
import com.isaque.sistemapedidos.model.Pedido;
import com.isaque.sistemapedidos.model.Produto;
import com.isaque.sistemapedidos.repository.PedidoRepository;
import com.isaque.sistemapedidos.repository.ProdutoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public void realizarPedido(CriarPedidoRequest request) {

        if(request.getNomeCliente() == null || request.getNomeCliente().isBlank()) {
            throw new IllegalArgumentException("Nome do cliente é obrigatório");
        }

        if(request.getItens() == null || request.getItens().isEmpty()) {
            throw new IllegalArgumentException("Pedido deve ter pelo menos um item");
        }

        Pedido pedido = new Pedido();
        pedido.setNomeCliente(request.getNomeCliente());

        double valorTotal = 0;

        for (ItemPedidoRequest itemRequest : request.getItens()) {

            if(itemRequest.getProdutoId() == null) {
                throw new IllegalArgumentException("Produto é obrigatório");
            }

            if(itemRequest.getQuantidade() == null || itemRequest.getQuantidade() <= 0) {
                throw new IllegalArgumentException("Quantidade deve ser maior que zero");
            }
            Produto produtoEstoque = produtoRepository.findById(itemRequest.getProdutoId())
                    .orElse(null);

            if (produtoEstoque == null) {
                throw new ProdutoNaoEncontradoException("Produto não encontrado");
            }

            if (produtoEstoque.getQuantidadeEstoque() < itemRequest.getQuantidade()) {
                throw new EstoqueInsuficienteException("Estoque insuficiente para: " + produtoEstoque.getNome());
            }

            produtoEstoque.setQuantidadeEstoque(produtoEstoque.getQuantidadeEstoque() - itemRequest.getQuantidade());

            produtoRepository.save(produtoEstoque);

            ItemPedido item = new ItemPedido();
            item.setProduto(produtoEstoque);
            item.setQuantidade(itemRequest.getQuantidade());
            item.setPedido(pedido);

            pedido.adicionarItem(item);

            valorTotal += produtoEstoque.getPreco() * itemRequest.getQuantidade();
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