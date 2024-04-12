package com.mindway.server.v2.domain.goal.repository;

import com.mindway.server.v2.domain.goal.entity.Goal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoalRepository extends JpaRepository<Goal, Long> {
}
