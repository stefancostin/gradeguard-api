package io.github.stefancostin.gradeguard.services;

import io.github.stefancostin.gradeguard.entities.User;
import io.github.stefancostin.gradeguard.models.AuthRequestDTO;
import io.github.stefancostin.gradeguard.models.AuthDTO;
import io.github.stefancostin.gradeguard.models.AuthResponseDTO;
import io.github.stefancostin.gradeguard.repositories.IUserRepository;
import org.jasypt.util.text.BasicTextEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AuthService {

    @Autowired
    private IUserRepository userRepository;
    @Resource
    private BasicTextEncryptor encryptor;

    public AuthResponseDTO isAuthenticated(AuthRequestDTO authRequest) {
        if (authRequest.getEmail() == null || authRequest.getPassword() == null) {
            return failAuthentication();
        }

        String email = authRequest.getEmail();
        String password = authRequest.getPassword();

        // encrypted version
        User user = userRepository.findByEmail(email).orElse(null);
        if (user == null) {
            return failAuthentication();
        }

        String decryptedPassword = encryptor.decrypt(user.getPassword());
        if (password.equals(decryptedPassword)) {
            return passAuthentication(user);
        }

        return failAuthentication();

        // unencrypted version
//        User authenticatedUser = userRepository.findByEmailAndPassword(email, password).orElse(null);
//
//        if (authenticatedUser == null) {
//            return failAuthentication();
//        }
//
//        return passAuthentication(authenticatedUser);
    }

    private AuthResponseDTO failAuthentication() {
        AuthResponseDTO response = new AuthResponseDTO();
        response.setAuthenticated(false);
        return response;
    }

    private AuthResponseDTO passAuthentication(User authenticatedUser) {
        AuthDTO authenticatedUserDTO = new AuthDTO();
        authenticatedUserDTO.setId(authenticatedUser.getId());
        authenticatedUserDTO.setName(authenticatedUser.getLastName() + " " + authenticatedUser.getFirstName());
        authenticatedUserDTO.setRole(authenticatedUser.getRole());

        AuthResponseDTO response = new AuthResponseDTO();
        response.setAuthenticated(true);
        response.setAuthenticatedUser(authenticatedUserDTO);

        return response;
    }

}
