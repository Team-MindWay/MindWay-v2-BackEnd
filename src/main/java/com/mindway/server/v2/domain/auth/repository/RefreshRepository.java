package com.mindway.server.v2.domain.auth.repository;

import com.mindway.server.v2.domain.auth.RefreshToken;
import org.springframework.data.repository.CrudRepository;

public interface RefreshRepository extends CrudRepository<RefreshToken, String> {
}
