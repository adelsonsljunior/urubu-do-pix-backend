package com.adelsonsljunior.controllers;

import com.adelsonsljunior.dtos.transaction.TransactionResponseDTO;
import com.adelsonsljunior.exceptions.ErrorResponse;

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

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

@Path(value = "/transactions")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Transaction", description = "Operations related to Transactions")
public class TransactionController {

    @Inject
    TransactionService transactionService;

    @POST()
    @Path("/deposit")
    @Operation(summary = "Create a new transaction", description = "Create a new transaction")
    @APIResponses(value = {
            @APIResponse(responseCode = "201", description = "Successful deposit", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = DepositRequestDTO.class))),
            @APIResponse(responseCode = "404", description = "User not found", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = ErrorResponse.class)))
    })
    public Response deposit(DepositRequestDTO data) {

        TransactionResponseDTO createdTransaction = transactionService.deposit(data);

        return Response.status(Response.Status.CREATED).entity(createdTransaction).build();

    }

    @GET()
    @Path("/{userId}")
    @Operation(summary = "List a user's transactions", description = "Returns all transactions of a user")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Returns a list of transactions successfully", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(type = SchemaType.ARRAY, implementation = TransactionResponseDTO.class))),
            @APIResponse(responseCode = "404", description = "User not found", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = ErrorResponse.class)))

    })
    public Response findAllByUserId(@PathParam("userId") Long userId) {
        List<TransactionResponseDTO> transactions = transactionService.findAllByUserId(userId);
        return Response.status(Response.Status.OK).entity(transactions).build();
    }

    @GET()
    @Path("/balance/{transactionId}/{currentDate}")
    @Operation(summary = "Current value of the yield", description = "Returns the current value of the yield")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Returns the curret value successfully", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = BalanceResponseDTO.class))),
            @APIResponse(responseCode = "404", description = "Transaction not found", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = ErrorResponse.class)))
    })
    public Response getBalance(@PathParam("transactionId") Long transactionId,
            @PathParam("currentDate") LocalDate currentDate) {

        BalanceResponseDTO balance = transactionService.getBalance(transactionId, currentDate);

        return Response.status(Response.Status.OK).entity(balance).build();

    }

}
