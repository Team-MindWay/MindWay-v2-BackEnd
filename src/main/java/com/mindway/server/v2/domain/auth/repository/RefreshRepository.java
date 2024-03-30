package com.mindway.server.v2.domain.auth.repository;

import com.mindway.server.v2.domain.auth.RefreshToken;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface RefreshRepository extends CrudRepository<RefreshToken, String> {
    Optional<RefreshToken> findByMemberId(UUID memberId);
}
