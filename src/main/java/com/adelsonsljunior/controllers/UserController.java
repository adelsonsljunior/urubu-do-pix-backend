package com.adelsonsljunior.controllers;

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
public class UserController {

    @Inject
    UserService userService;

    @POST
    public Response create(UserRequestDTO data) {

        UserResponseDTO createdUser = userService.create(data);
        return Response.status(Response.Status.CREATED).entity(createdUser).build();
    }
}
