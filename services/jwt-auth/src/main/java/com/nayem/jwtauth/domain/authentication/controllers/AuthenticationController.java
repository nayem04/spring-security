package com.nayem.jwtauth.domain.authentication.controllers;

import com.nayem.jwtauth.common.exceptions.InvalidCredentialException;
import com.nayem.jwtauth.common.routing.Router;
import com.nayem.jwtauth.domain.authentication.dtos.AuthenticationRequestDto;
import com.nayem.jwtauth.domain.authentication.dtos.AuthenticationResponseDto;
import com.nayem.jwtauth.domain.authentication.services.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping(Router.LOGIN)
    public ResponseEntity<AuthenticationResponseDto> login(@Valid @RequestBody AuthenticationRequestDto authenticationRequestDto) throws InvalidCredentialException {
        return ResponseEntity.ok(authenticationService.login(authenticationRequestDto));
    }
}
