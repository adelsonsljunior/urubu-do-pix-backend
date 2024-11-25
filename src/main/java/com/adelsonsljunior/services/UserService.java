package com.adelsonsljunior.services;

import com.adelsonsljunior.dtos.user.UserRequestDTO;
import com.adelsonsljunior.dtos.user.UserResponseDTO;
import com.adelsonsljunior.entities.User;
import com.adelsonsljunior.repositories.UserRepository;
import jakarta.inject.Inject;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class UserService {

    @Inject
    UserRepository userRepository;

    @Transactional
    public UserResponseDTO create(UserRequestDTO userRequestDTO) {
        User user = new User(userRequestDTO);
        userRepository.persist(user);
        return user.toResponse();
    }
}
