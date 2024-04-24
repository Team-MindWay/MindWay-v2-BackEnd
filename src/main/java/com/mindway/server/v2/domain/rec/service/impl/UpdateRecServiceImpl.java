package com.mindway.server.v2.domain.rec.service.impl;

import com.mindway.server.v2.domain.order.exception.NotAccessStudentException;
import com.mindway.server.v2.domain.rec.entity.Rec;
import com.mindway.server.v2.domain.rec.execption.NotFoundRecommendException;
import com.mindway.server.v2.domain.rec.presentation.dto.reqest.UpdateRecRequest;
import com.mindway.server.v2.domain.rec.repository.RecRepository;
import com.mindway.server.v2.domain.rec.service.UpdateRecService;
import com.mindway.server.v2.domain.user.entity.Authority;
import com.mindway.server.v2.domain.user.entity.User;
import com.mindway.server.v2.domain.user.util.UserUtil;
import com.mindway.server.v2.global.annotation.ServiceWithTransaction;
import lombok.RequiredArgsConstructor;

@ServiceWithTransaction
@RequiredArgsConstructor
public class UpdateRecServiceImpl implements UpdateRecService {

    private final UserUtil userUtil;
    private final RecRepository recRepository;

    public void execute(Long id, UpdateRecRequest updateRecRequest) {
        User user = userUtil.getCurrentUser();
        Rec rec = recRepository.findById(id)
                .orElseThrow(NotFoundRecommendException::new);

        if (user.getAuthority() == Authority.ROLE_STUDENT){
            throw new NotAccessStudentException();
        }

        rec.updateRec(updateRecRequest);

        recRepository.save(rec);
    }
}
