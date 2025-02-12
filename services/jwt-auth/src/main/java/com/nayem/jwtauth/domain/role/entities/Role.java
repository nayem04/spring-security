package com.nayem.jwtauth.domain.role.entities;

import com.nayem.jwtauth.common.base.BaseEntity;
import com.nayem.jwtauth.domain.user.entities.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;

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

    @ManyToMany(mappedBy = "roles")
    private Set<User> users;
}
