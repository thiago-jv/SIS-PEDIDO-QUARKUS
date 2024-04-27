package org.cliente.api.v1.handler;

import org.cliente.api.v1.handler.exception.ExceptionDTO;

public class CustomException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    private int statusCode;

    private ExceptionDTO exceptionDTO;

    public CustomException(String message) {
        super(message);
    }

    public CustomException(int statusCode, ExceptionDTO exceptionDTO) {
        this.statusCode = statusCode;
        this.exceptionDTO = exceptionDTO;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public ExceptionDTO getExceptionDTO() {
        return exceptionDTO;
    }

}

