package com.mindway.server.v2.domain.event.service.impl;

import com.mindway.server.v2.domain.event.entity.Event;
import com.mindway.server.v2.domain.event.exception.NotFoundEventException;
import com.mindway.server.v2.domain.event.repository.EventRepository;
import com.mindway.server.v2.domain.event.service.EventDeleteService;
import com.mindway.server.v2.domain.notice.exception.NotAccessStudentException;
import com.mindway.server.v2.domain.user.entity.Authority;
import com.mindway.server.v2.domain.user.entity.User;
import com.mindway.server.v2.domain.user.util.UserUtil;
import com.mindway.server.v2.global.annotation.ServiceWithTransaction;
import com.mindway.server.v2.global.thirdparty.aws.S3Util;
import lombok.RequiredArgsConstructor;

@ServiceWithTransaction
@RequiredArgsConstructor
public class EventDeleteServiceImpl implements EventDeleteService {

    private final EventRepository eventRepository;
    private final UserUtil userUtil;
    private final S3Util s3Util;

    public void execute(Long id) {
        User user = userUtil.getCurrentUser();

        if (user.getAuthority() == Authority.ROLE_STUDENT)
            throw new NotAccessStudentException();

        Event event = eventRepository.findById(id)
                .orElseThrow(NotFoundEventException::new);

        s3Util.deleteImage(event.getImg_url());

        eventRepository.deleteById(id);
    }
}
