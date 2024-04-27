/**
 * @author Thiago henrique on 12/04/2024
 */


package org.cliente.api.v1.handler;

import org.cliente.api.v1.handler.exception.Exception;
public class BusinnesException extends Exception {

    public BusinnesException(String exception) {
        super(exception);
    }


}
