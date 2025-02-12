package com.nayem.jwtauth.domain.authentication.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthenticationRequestDto {
    @NotBlank(message = "Username is required.")
    @JsonProperty(value = "username", required = true)
    private String username;

    @NotBlank(message = "Password is required.")
    @Size(min = 6, message = "Password length must be six digits or more")
    @JsonProperty(value = "password", required = true)
    private String password;
}
