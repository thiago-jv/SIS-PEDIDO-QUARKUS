/**
 * @author Thiago henrique on 09/04/2024
 */


/**
 * @author Thiago henrique on 08/04/2024
 */

package org.cliente.api.v1.handler;

import org.cliente.api.v1.handler.exception.Exception;

public class NotFoundException extends Exception {
    public NotFoundException(String exception) {
        super(exception);
    }

}
