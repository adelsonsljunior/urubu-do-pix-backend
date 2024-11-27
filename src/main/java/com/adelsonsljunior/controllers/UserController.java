package com.adelsonsljunior.controllers;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import com.adelsonsljunior.dtos.user.UserRequestDTO;
import com.adelsonsljunior.dtos.user.UserResponseDTO;
import com.adelsonsljunior.services.UserService;
import jakarta.inject.Inject;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path(value = "/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "User", description = "Operations related to Users")
public class UserController {

    @Inject
    UserService userService;

    @POST
    @Operation(summary = "Create a new user")
    @APIResponse(responseCode = "201", description = "User created successfully", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = UserResponseDTO.class)))

    public Response create(UserRequestDTO data) {

        UserResponseDTO createdUser = userService.create(data);
        return Response.status(Response.Status.CREATED).entity(createdUser).build();
    }
}
