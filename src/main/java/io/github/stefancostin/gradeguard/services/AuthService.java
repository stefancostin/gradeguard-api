package io.github.stefancostin.gradeguard.services;

import io.github.stefancostin.gradeguard.entities.User;
import io.github.stefancostin.gradeguard.models.AuthRequestDTO;
import io.github.stefancostin.gradeguard.models.AuthDTO;
import io.github.stefancostin.gradeguard.models.AuthResponseDTO;
import io.github.stefancostin.gradeguard.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    IUserRepository userRepository;

    public AuthResponseDTO isAuthenticated(AuthRequestDTO authRequest) {
        if (authRequest.getEmail() == null || authRequest.getPassword() == null) {
            return failAuthentication();
        }

        String email = authRequest.getEmail();
        String password = authRequest.getPassword();
        User authenticatedUser = userRepository.findByEmailAndPassword(email, password).orElse(null);

        if (authenticatedUser == null) {
            return failAuthentication();
        }

        return passAuthentication(authenticatedUser);
    }

    private AuthResponseDTO failAuthentication() {
        AuthResponseDTO response = new AuthResponseDTO();
        response.setAuthenticated(false);
        return response;
    }

    private AuthResponseDTO passAuthentication(User authenticatedUser) {
        AuthDTO authenticatedUserDTO = new AuthDTO();
        authenticatedUserDTO.setUserId(authenticatedUser.getId());
        authenticatedUserDTO.setUserName(authenticatedUser.getLastName() + " " + authenticatedUser.getFirstName());
        authenticatedUserDTO.setUserRole(authenticatedUser.getRole());

        AuthResponseDTO response = new AuthResponseDTO();
        response.setAuthenticated(true);
        response.setAuthenticatedUser(authenticatedUserDTO);

        return response;
    }

}
