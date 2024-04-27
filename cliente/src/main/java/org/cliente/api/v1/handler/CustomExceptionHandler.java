package org.cliente.api.v1.handler;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class CustomExceptionHandler implements ExceptionMapper<CustomException> {

    private static final int CONFLICT = 409;
    private static final int NOT_FOUND = 404;
    private static final int INTERNAL_SERVER_ERROR = 500;

    @Override
    public Response toResponse(CustomException exception) {
        switch (exception.getStatusCode()) {
            case NOT_FOUND:
                return Response.status(Response.Status.NOT_FOUND).entity(exception.getExceptionDTO()).build();
            case CONFLICT:
                return Response.status(Response.Status.CONFLICT).entity(exception.getExceptionDTO()).build();
            case INTERNAL_SERVER_ERROR:
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(exception.getMessage()).build();
            default:
                return Response.status(Response.Status.BAD_REQUEST).entity(exception.getMessage()).build();
        }
    }
}
