package com.mindway.server.v2.domain.rank.repository;

import com.mindway.server.v2.domain.rank.entity.Ranks;
import com.mindway.server.v2.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RankRepository extends JpaRepository<Ranks, Long> {
    List<Ranks> findTop3ByOrderByAccrueDesc();

    Ranks findByUser(User user);
}
