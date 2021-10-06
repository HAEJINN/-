package com.ecommerce.domain.user.domain;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.Entity;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    @EntityGraph(attributePaths = {"wallet", "photo"})
    Optional<User> findByEmail(String email);

    @EntityGraph(attributePaths = {"wallet", "photo"})
    Optional<User> findById(Long id);

}
