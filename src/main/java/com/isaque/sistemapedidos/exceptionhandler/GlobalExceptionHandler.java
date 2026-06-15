package com.isaque.sistemapedidos.exceptionhandler;

import com.isaque.sistemapedidos.response.ErrorResponse;
import java.time.LocalDateTime;
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
    public ErrorResponse tratarProdutoNaoEncontrado(ProdutoNaoEncontradoException ex) {
        return new ErrorResponse(
                LocalDateTime.now().toString(),
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage()
        );
    }

    @ExceptionHandler(ProdutoDuplicadoException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse tratarProdutoDuplicado(ProdutoDuplicadoException ex) {
       return new ErrorResponse(LocalDateTime.now().toString(),
                HttpStatus.CONFLICT.value(),
                ex.getMessage()
        );
    }

    @ExceptionHandler(PedidoNaoEncontradoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse tratarPedidoNaoEncontrado(
            PedidoNaoEncontradoException ex) {

        return new ErrorResponse(
                LocalDateTime.now().toString(),
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage()
        );
    }

}
