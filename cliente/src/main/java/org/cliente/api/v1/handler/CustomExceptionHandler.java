package org.cliente.api.v1.handler;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Provider
public class CustomExceptionHandler implements ExceptionMapper<CustomException> {

    @Override
    public Response toResponse(CustomException e) {
        LocalDateTime dataHoraAtual = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return Response.status(Response.Status.BAD_REQUEST).
                entity(new DetalheMensagem(e.getMessage(), Response.Status.BAD_REQUEST.getStatusCode(), dataHoraAtual.format(formatter)))
                .build();
    }
}
