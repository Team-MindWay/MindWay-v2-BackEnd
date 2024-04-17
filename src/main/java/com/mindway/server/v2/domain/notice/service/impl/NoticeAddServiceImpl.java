package com.mindway.server.v2.domain.notice.service.impl;

import com.mindway.server.v2.domain.notice.converter.NoticeConverter;
import com.mindway.server.v2.domain.notice.entity.Notice;
import com.mindway.server.v2.domain.notice.presentation.dto.request.NoticeAddRequestDto;
import com.mindway.server.v2.domain.notice.repository.NoticeRepository;
import com.mindway.server.v2.domain.notice.service.NoticeAddService;
import com.mindway.server.v2.domain.user.entity.User;
import com.mindway.server.v2.domain.user.util.UserUtil;
import com.mindway.server.v2.global.annotation.ServiceWithTransaction;
import lombok.RequiredArgsConstructor;

@ServiceWithTransaction
@RequiredArgsConstructor
public class NoticeAddServiceImpl implements NoticeAddService {

    private final NoticeConverter noticeConverter;
    private final NoticeRepository noticeRepository;
    private final UserUtil userUtil;

    public void execute(NoticeAddRequestDto noticeAddRequestDto) {
        User user = userUtil.getCurrentUser();

        Notice notice = noticeConverter.toEntity(noticeAddRequestDto, user);

        noticeRepository.save(notice);
    }
}
