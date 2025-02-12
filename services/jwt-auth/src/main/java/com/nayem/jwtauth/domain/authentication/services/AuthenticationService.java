package com.nayem.jwtauth.domain.authentication.services;

import com.nayem.jwtauth.common.exceptions.InvalidCredentialException;
import com.nayem.jwtauth.common.utilities.ExceptionUtil;
import com.nayem.jwtauth.common.utilities.JwtUtil;
import com.nayem.jwtauth.domain.authentication.dtos.AuthenticationRequestDto;
import com.nayem.jwtauth.domain.authentication.dtos.AuthenticationResponseDto;
import com.nayem.jwtauth.domain.user.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponseDto login(AuthenticationRequestDto authenticationRequestDto) throws InvalidCredentialException {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationRequestDto.getUsername(),
                        authenticationRequestDto.getPassword()));

        if (!authentication.isAuthenticated()) {
            throw ExceptionUtil.getInvalidCredentialException(authenticationRequestDto.getUsername(), authenticationRequestDto.getPassword());
        }

        User user = (User) authentication.getPrincipal();
        Collection<String> roles = user.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).toList();

        AuthenticationResponseDto authenticationResponseDto = new AuthenticationResponseDto();
        authenticationResponseDto.setAccessToken(JwtUtil.generateToken(user.getUsername(), roles));

        return authenticationResponseDto;

    }
}
