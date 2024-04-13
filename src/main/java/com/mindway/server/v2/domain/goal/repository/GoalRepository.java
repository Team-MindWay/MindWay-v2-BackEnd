package com.mindway.server.v2.domain.goal.repository;

import com.mindway.server.v2.domain.goal.entity.Goal;
import com.mindway.server.v2.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GoalRepository extends JpaRepository<Goal, Long> {
    Boolean existsByUser(User user);
    Optional<Goal> findByUser(User user);
}
