package com.adelsonsljunior.controllers;

import com.adelsonsljunior.dtos.transaction.TransactionResponseDTO;
import com.adelsonsljunior.dtos.transaction.TransactionRequestDTO;
import com.adelsonsljunior.services.TransactionService;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path(value = "/transactions")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TransactionController {

    @Inject
    TransactionService transactionService;

    @POST()
    @Path("/deposit")
    public Response deposit(TransactionRequestDTO data) {

        TransactionResponseDTO createdTransaction = transactionService.deposit(data);

        return Response.status(Response.Status.CREATED).entity(createdTransaction).build();

    }

}