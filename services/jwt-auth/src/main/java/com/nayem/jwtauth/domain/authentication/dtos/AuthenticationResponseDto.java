package com.nayem.jwtauth.domain.authentication.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthenticationResponseDto {
    @NotBlank(message = "Access Token is required.")
    @JsonProperty(value = "access_token", required = true)
    private String accessToken;
}
