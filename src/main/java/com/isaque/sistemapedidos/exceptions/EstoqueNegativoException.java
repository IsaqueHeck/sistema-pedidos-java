package com.isaque.sistemapedidos.exceptions;

public class EstoqueNegativoException extends RuntimeException {
    public EstoqueNegativoException(String message) {
        super(message);
    }
}
