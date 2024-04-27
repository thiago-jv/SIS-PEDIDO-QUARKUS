/**
 * @author Thiago henrique on 12/04/2024
 */


package org.cliente.api.v1.handler;

import org.cliente.api.v1.handler.exception.Exception;

public class ConflictException extends Exception {

    public ConflictException(String exception) {
        super(exception);
    }

}
