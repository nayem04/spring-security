package com.nayem.jwtauth.domain.user.repositories;

import com.nayem.jwtauth.domain.user.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u WHERE :query IS NULL OR u.firstName LIKE %:query% OR u.lastName like %:query%")
    Page<User> search(@Param("query") String query, Pageable pageable);

    Optional<User> findByUsername(String username);
}
