package io.github.stefancostin.gradeguard.controllers;

import io.github.stefancostin.gradeguard.models.AuthRequestDTO;
import io.github.stefancostin.gradeguard.models.AuthDTO;
import io.github.stefancostin.gradeguard.models.AuthResponseDTO;
import io.github.stefancostin.gradeguard.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    AuthService authService;

    @RequestMapping(method = RequestMethod.POST)
    public AuthResponseDTO login(@RequestBody AuthRequestDTO authRequest) {
        return authService.isAuthenticated(authRequest);
    }

}
