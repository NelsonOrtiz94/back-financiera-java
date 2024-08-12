package com.entidad.financiera.exception;

public class ClienteNotFoundException extends RuntimeException {
    public ClienteNotFoundException(String message) {
        super(message);
    }

    public static class CuentaNoEncontradaException extends RuntimeException {
        public CuentaNoEncontradaException(String mensaje) {
            super(mensaje);
        }
    }

}

