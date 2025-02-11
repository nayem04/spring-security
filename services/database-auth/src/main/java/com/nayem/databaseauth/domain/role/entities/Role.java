package com.nayem.databaseauth.domain.role.entities;

import com.nayem.databaseauth.common.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "roles")
@Getter
@Setter
@ToString
public class Role extends BaseEntity {
    @Column(name = "description")
    private String description;

    @Column(name = "label", nullable = false, unique = true)
    private String label;

    @Column(name = "name", nullable = false, unique = true)
    private String name;
}
