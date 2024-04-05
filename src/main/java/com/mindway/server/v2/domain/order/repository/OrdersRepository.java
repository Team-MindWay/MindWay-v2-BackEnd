package com.mindway.server.v2.domain.order.repository;

import com.mindway.server.v2.domain.order.entity.Orders;
import com.mindway.server.v2.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrdersRepository extends JpaRepository<Orders, Long> {
    List<Orders> findByUser(User user);
}
