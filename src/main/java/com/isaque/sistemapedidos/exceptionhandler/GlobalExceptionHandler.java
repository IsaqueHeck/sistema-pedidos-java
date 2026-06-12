package com.isaque.sistemapedidos.exceptionhandler;

import com.isaque.sistemapedidos.exceptions.PedidoNaoEncontradoException;
import com.isaque.sistemapedidos.exceptions.ProdutoDuplicadoException;
import com.isaque.sistemapedidos.exceptions.ProdutoNaoEncontradoException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ProdutoNaoEncontradoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String tratarProdutoNaoEncontrado(ProdutoNaoEncontradoException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(ProdutoDuplicadoException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String tratarProdutoDuplicado(ProdutoDuplicadoException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(PedidoNaoEncontradoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String tratarPedidoNaoEncontrado(
            PedidoNaoEncontradoException ex) {

        return ex.getMessage();
    }

}
