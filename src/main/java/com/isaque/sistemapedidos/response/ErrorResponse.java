package com.isaque.sistemapedidos.response;

public class ErrorResponse {
    private String timestamp;
    private int status;
    private String erro;

    public ErrorResponse(String timestamp, int status, String erro) {
        this.timestamp = timestamp;
        this.status = status;
        this.erro = erro;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public int getStatus() {
        return status;
    }

    public String getErro() {
        return erro;
    }

}
