/**
 * @author Thiago henrique on 27/04/2024
 */


package org.cliente.api.v1.handler.exception;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
public class ExceptionDTO implements Serializable {

    private String message;
    protected String dateTime;

    public ExceptionDTO(String message) {
        this.message = message;
        this.dateTime = actualDate();
    }

    protected String actualDate(){
        LocalDateTime dataHoraAtual = LocalDateTime.now();
        return dataHoraAtual.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
    }
}

