package com.nayem.jwtauth.domain.role.repositories;

import com.nayem.jwtauth.domain.role.entities.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    @Query("SELECT r FROM Role r WHERE :query IS NULL OR r.name LIKE %:query%")
    Page<Role> search(@Param("query") String query, Pageable pageable);
}
