package com.mindway.server.v2.domain.notice.service.impl;

import com.mindway.server.v2.domain.notice.converter.NoticeConverter;
import com.mindway.server.v2.domain.notice.entity.Notice;
import com.mindway.server.v2.domain.notice.exception.NotFoundNoticeException;
import com.mindway.server.v2.domain.notice.presentation.dto.response.NoticeGetResponseDto;
import com.mindway.server.v2.domain.notice.repository.NoticeRepository;
import com.mindway.server.v2.domain.notice.service.NoticeGetService;
import com.mindway.server.v2.global.annotation.ServiceWithReadOnlyTransaction;
import lombok.RequiredArgsConstructor;

@ServiceWithReadOnlyTransaction
@RequiredArgsConstructor
public class NoticeGetServiceImpl implements NoticeGetService {

    private final NoticeRepository noticeRepository;
    private final NoticeConverter noticeConverter;

    public NoticeGetResponseDto execute() {
        Notice latestNotice = noticeRepository.findNoticeWithMaxN();

        if (latestNotice == null) {
            throw new NotFoundNoticeException();
        }

        return noticeConverter.toDto(latestNotice);
    }
}
