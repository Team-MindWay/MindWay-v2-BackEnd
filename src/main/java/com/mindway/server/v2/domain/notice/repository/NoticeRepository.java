package com.mindway.server.v2.domain.notice.repository;


import com.mindway.server.v2.domain.notice.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<Notice, Long> {
}
