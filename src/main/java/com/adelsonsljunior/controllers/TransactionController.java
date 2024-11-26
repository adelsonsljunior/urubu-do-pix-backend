package com.adelsonsljunior.controllers;

import com.adelsonsljunior.dtos.transaction.TransactionResponseDTO;

import java.time.LocalDate;
import java.util.List;

import com.adelsonsljunior.dtos.transaction.BalanceResponseDTO;
import com.adelsonsljunior.dtos.transaction.DepositRequestDTO;
import com.adelsonsljunior.services.TransactionService;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
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
    public Response deposit(DepositRequestDTO data) {

        TransactionResponseDTO createdTransaction = transactionService.deposit(data);

        return Response.status(Response.Status.CREATED).entity(createdTransaction).build();

    }

    @GET()
    @Path("/{userId}")
    public Response findAllByUserId(@PathParam("userId") Long userId) {
        List<TransactionResponseDTO> transactions = transactionService.findAllByUserId(userId);
        return Response.status(Response.Status.OK).entity(transactions).build();
    }

    @GET()
    @Path("/balance/{transactionId}/{currentDate}")
    public Response getBalance(@PathParam("transactionId") Long transactionId,
            @PathParam("currentDate") LocalDate currentDate) {

        BalanceResponseDTO balance = transactionService.getBalance(transactionId, currentDate);

        return Response.status(Response.Status.OK).entity(balance).build();

    }

}
