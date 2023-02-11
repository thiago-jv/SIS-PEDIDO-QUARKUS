package org.cliente.api.v1.handler;

import java.io.Serializable;

public class CustomException extends
        RuntimeException implements Serializable {

    private static final long serialVersionUID = 1L;

    public CustomException(String mensagem) {
        super(mensagem);
    }

}
