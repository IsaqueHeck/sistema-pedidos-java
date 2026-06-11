package com.isaque.sistemapedidos.exceptions;

public class PrecoNegativoException extends RuntimeException {
    public PrecoNegativoException(String message) {
        super(message);
    }
}
