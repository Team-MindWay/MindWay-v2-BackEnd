package com.mindway.server.v2.domain.event.service.impl;

import com.mindway.server.v2.domain.event.util.EventConverter;
import com.mindway.server.v2.domain.event.entity.Event;
import com.mindway.server.v2.domain.event.entity.Status;
import com.mindway.server.v2.domain.event.exception.InvalidStartAndEndDateException;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

@ServiceWithTransaction
@RequiredArgsConstructor
public class EventWriteServiceImpl implements EventWriteService {

    private final EventRepository eventRepository;
    private final UserUtil userUtil;
    private final EventConverter eventConverter;

    public void execute(EventWriteRequestDto eventWriteRequestDto) {
        User user = userUtil.getCurrentUser();

        if (user.getAuthority() == Authority.ROLE_STUDENT)
            throw new NotAccessStudentException();

        Status status = checkDate(eventWriteRequestDto.getStarted_at(), eventWriteRequestDto.getEnded_at());

        Event event = eventConverter.toEntity(eventWriteRequestDto, user, eventWriteRequestDto.getImg_url(), status);

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
