package com.ecommerce.domain.item.domain;

import com.ecommerce.domain.user.domain.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item,Long> {
    Optional<Item> findByTokenId(BigInteger tokenId);
    @EntityGraph(attributePaths = {"user"})
    List<Item> findByUser(User user);
}
