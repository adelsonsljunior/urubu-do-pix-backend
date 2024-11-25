package com.adelsonsljunior.exceptions;

import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class ResourceNotFoundMapper implements ExceptionMapper<ResourceNotFoundException> {

    @Override
    @Produces(MediaType.APPLICATION_JSON)
    public Response toResponse(ResourceNotFoundException e) {

        ErrorResponse response = new ErrorResponse(e.getMessage());
        return Response.status(Response.Status.NOT_FOUND)
                .entity(response)
                .build();
    }

}
