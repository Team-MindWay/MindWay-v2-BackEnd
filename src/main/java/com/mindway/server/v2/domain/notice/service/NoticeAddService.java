package com.mindway.server.v2.domain.notice.service;

import com.mindway.server.v2.domain.notice.presentation.dto.request.NoticeAddRequestDto;

public interface NoticeAddService {
    void execute(NoticeAddRequestDto noticeAddRequestDto);
}
