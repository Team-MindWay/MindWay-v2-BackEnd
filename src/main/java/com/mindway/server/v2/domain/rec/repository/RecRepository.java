package com.mindway.server.v2.domain.rec.repository;

import com.mindway.server.v2.domain.rec.entity.Rec;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecRepository extends JpaRepository<Rec, Long> {
}
