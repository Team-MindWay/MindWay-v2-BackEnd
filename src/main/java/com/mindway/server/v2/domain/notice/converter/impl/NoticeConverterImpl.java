package com.mindway.server.v2.domain.notice.converter.impl;

import com.mindway.server.v2.domain.notice.converter.NoticeConverter;
import com.mindway.server.v2.domain.notice.entity.Notice;
import com.mindway.server.v2.domain.notice.presentation.dto.request.NoticeAddRequestDto;
import com.mindway.server.v2.domain.notice.presentation.dto.response.NoticeGetResponseDto;
import com.mindway.server.v2.domain.user.entity.User;
import org.springframework.stereotype.Component;

@Component
public class NoticeConverterImpl implements NoticeConverter {

    public Notice toEntity(NoticeAddRequestDto noticeAddRequestDto, User user) {
        return Notice.builder()
                .title(noticeAddRequestDto.getTitle())
                .content(noticeAddRequestDto.getContent())
                .user(user)
                .build();
    }

    public NoticeGetResponseDto toDto(Notice notice) {
        return NoticeGetResponseDto.builder()
                .title(notice.getTitle())
                .content(notice.getContent())
                .build();
    }
}
