package com.nayem.databaseauth.common.base;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/* In JPA (Java Persistence API), @MappedSuperclass is an annotation
used to designate a class as a mapped superclass, meaning its properties
are mapped to entities that inherit from it, but the class itself is not an entity.
This is useful when you want to share common fields or functionality
across multiple entities without creating a separate table for the superclass. */
@MappedSuperclass
@Getter
@Setter
public abstract class BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "created", nullable = false)
    private Date created;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "last_updated", nullable = false)
    private Date lastUpdated;

    /* @PrePersist annotation is used to mark a method that should be called
    before an entity is persisted for the first time. It marks a method in an entity class
    that should be executed right before the entity is inserted into the database. */
    @PrePersist
    private void onBaseCreate() {
        this.created = new Date();
        this.lastUpdated = new Date();
    }

    /* @PreUpdate annotation is used to mark a method that should be called
    before an entity is updated in the database. */
    @PreUpdate
    private void onBaseUpdate() {
        this.lastUpdated = new Date();
    }
}
