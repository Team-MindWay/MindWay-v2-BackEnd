package com.mindway.server.v2.domain.rec.service.impl;

import com.mindway.server.v2.domain.order.exception.NotAccessStudentException;
import com.mindway.server.v2.domain.rec.entity.Rec;
import com.mindway.server.v2.domain.rec.entity.Type;
import com.mindway.server.v2.domain.rec.presentation.dto.reqest.WriteRecRequest;
import com.mindway.server.v2.domain.rec.repository.RecRepository;
import com.mindway.server.v2.domain.rec.service.WriteRecService;
import com.mindway.server.v2.domain.rec.util.RecConverter;
import com.mindway.server.v2.domain.user.entity.Authority;
import com.mindway.server.v2.domain.user.entity.User;
import com.mindway.server.v2.domain.user.util.UserUtil;
import com.mindway.server.v2.global.annotation.ServiceWithTransaction;
import lombok.RequiredArgsConstructor;

@ServiceWithTransaction
@RequiredArgsConstructor
public class WriteRecServiceImpl implements WriteRecService {

    private final RecRepository recRepository;
    private final RecConverter recConverter;
    private final UserUtil userUtil;

    public void execute(WriteRecRequest writeRecRequest, Type type) {
        User user = userUtil.getCurrentUser();

        if (user.getAuthority() == Authority.ROLE_STUDENT) {
            throw new NotAccessStudentException();
        }

        recRepository.save(recConverter.toEntity(writeRecRequest, type));
    }
}
