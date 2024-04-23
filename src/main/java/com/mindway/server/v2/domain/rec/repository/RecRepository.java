package com.mindway.server.v2.domain.rec.repository;

import com.mindway.server.v2.domain.rec.entity.Rec;
import com.mindway.server.v2.domain.rec.entity.Type;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecRepository extends JpaRepository<Rec, Long> {
    List<Rec> findByType (Type type);
}
