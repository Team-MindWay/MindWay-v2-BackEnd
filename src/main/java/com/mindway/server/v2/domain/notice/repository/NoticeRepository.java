package com.mindway.server.v2.domain.notice.repository;


import com.mindway.server.v2.domain.notice.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface NoticeRepository extends JpaRepository<Notice, Long> {

    @Query("SELECT n FROM Notice n WHERE n.id = (SELECT MAX(n2.id) FROM Notice n2)")
    Notice findNoticeWithMaxN();

}
