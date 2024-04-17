package com.mindway.server.v2.domain.notice.converter;


import com.mindway.server.v2.domain.notice.entity.Notice;
import com.mindway.server.v2.domain.notice.presentation.dto.request.NoticeAddRequestDto;
import com.mindway.server.v2.domain.user.entity.User;

public interface NoticeConverter {
    Notice toEntity(NoticeAddRequestDto noticeAddRequestDto, User user);
}
