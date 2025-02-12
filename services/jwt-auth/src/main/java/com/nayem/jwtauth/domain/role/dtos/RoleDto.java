package com.nayem.jwtauth.domain.role.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nayem.jwtauth.common.base.BaseDto;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RoleDto extends BaseDto {
    @JsonProperty(value = "description")
    private String description;

    @NotBlank(message = "Label is required")
    @JsonProperty(value = "label", required = true)
    private String label;

    @NotBlank(message = "Name is required")
    @JsonProperty(value = "name", required = true)
    private String name;
}
