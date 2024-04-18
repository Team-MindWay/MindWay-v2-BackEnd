package com.mindway.server.v2.domain.event.service.impl;

import com.mindway.server.v2.domain.event.converter.EventConverter;
import com.mindway.server.v2.domain.event.entity.Event;
import com.mindway.server.v2.domain.event.presentation.dto.request.EventWriteRequestDto;
import com.mindway.server.v2.domain.event.repository.EventRepository;
import com.mindway.server.v2.domain.event.service.EventWriteService;
import com.mindway.server.v2.domain.notice.exception.NotAccessStudentException;
import com.mindway.server.v2.domain.user.entity.Authority;
import com.mindway.server.v2.domain.user.entity.User;
import com.mindway.server.v2.domain.user.util.UserUtil;
import com.mindway.server.v2.global.annotation.ServiceWithTransaction;
import com.mindway.server.v2.global.thirdparty.aws.S3Util;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@ServiceWithTransaction
@RequiredArgsConstructor
public class EventWriteServiceImpl implements EventWriteService {

    private final EventRepository eventRepository;
    private final S3Util s3Util;
    private final UserUtil userUtil;
    private final EventConverter eventConverter;

    public void execute(EventWriteRequestDto eventWriteRequestDto, MultipartFile image) {
        User user = userUtil.getCurrentUser();

        if (user.getAuthority() == Authority.ROLE_STUDENT)
            throw new NotAccessStudentException();

        String image_url = s3Util.imageUpload(image);

        Event event = eventConverter.toEntity(eventWriteRequestDto, user, image_url);

        eventRepository.save(event);
    }
}
