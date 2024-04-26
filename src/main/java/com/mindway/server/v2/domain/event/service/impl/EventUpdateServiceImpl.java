package com.mindway.server.v2.domain.event.service.impl;

import com.mindway.server.v2.domain.event.converter.EventConverter;
import com.mindway.server.v2.domain.event.entity.Event;
import com.mindway.server.v2.domain.event.entity.Status;
import com.mindway.server.v2.domain.event.exception.InvalidStartAndEndDateException;
import com.mindway.server.v2.domain.event.exception.InvalidUserEventException;
import com.mindway.server.v2.domain.event.exception.NotFoundEventException;
import com.mindway.server.v2.domain.event.presentation.dto.request.EventUpdateRequestDto;
import com.mindway.server.v2.domain.event.repository.EventRepository;
import com.mindway.server.v2.domain.event.service.EventUpdateService;
import com.mindway.server.v2.domain.notice.exception.NotAccessStudentException;
import com.mindway.server.v2.domain.user.entity.Authority;
import com.mindway.server.v2.domain.user.entity.User;
import com.mindway.server.v2.domain.user.util.UserUtil;
import com.mindway.server.v2.global.annotation.ServiceWithTransaction;
import com.mindway.server.v2.global.thirdparty.aws.S3Util;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

@ServiceWithTransaction
@RequiredArgsConstructor
public class EventUpdateServiceImpl implements EventUpdateService {

    private final EventRepository eventRepository;
    private final UserUtil userUtil;
    private final S3Util s3Util;
    private final EventConverter eventConverter;

    public void execute(Long eventId, EventUpdateRequestDto eventUpdateRequestDto, MultipartFile image) {
        User user = userUtil.getCurrentUser();

        Event findEvent = eventRepository.findById(eventId)
                .orElseThrow(NotFoundEventException::new);

        if (findEvent.getUser() != user)
            throw new InvalidUserEventException();

        if (user.getAuthority() == Authority.ROLE_STUDENT)
            throw new NotAccessStudentException();

        String img_url = s3Util.imageUpload(image);

        Status status = checkDate(eventUpdateRequestDto.getCreated_at(), eventUpdateRequestDto.getEnded_at());

        Event event = eventConverter.toUpdateEntity(eventId, eventUpdateRequestDto, user, img_url, status);

        eventRepository.save(event);
    }

    private Status checkDate(String started_date, String ended_date) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

            Date now = formatter.parse(String.valueOf(LocalDate.now()));
            Date start = formatter.parse(started_date);
            Date end = formatter.parse(ended_date);

            if (start.equals(now) && (start.equals(end) || start.before(end))) {
                return Status.NOW;
            } else if (start.after(now) && (start.equals(end) || start.before(end))) {
                return Status.PENDING;
            } else {
                throw new InvalidStartAndEndDateException();
            }

        } catch (ParseException e) {}

        return null;
    }
}
