package com.nayem.jwtauth.common.base;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public abstract class BaseDto implements Serializable {
    @JsonProperty(value = "id")
    private Long id;

    @JsonProperty(value = "created", access = JsonProperty.Access.READ_ONLY)
    private Date created;

    @JsonProperty(value = "last_updated", access = JsonProperty.Access.READ_ONLY)
    private Date lastUpdated;
}
