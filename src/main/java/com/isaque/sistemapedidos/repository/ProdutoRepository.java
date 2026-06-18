package com.isaque.sistemapedidos.repository;

import com.isaque.sistemapedidos.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository
        extends JpaRepository<Produto, Integer> {
}