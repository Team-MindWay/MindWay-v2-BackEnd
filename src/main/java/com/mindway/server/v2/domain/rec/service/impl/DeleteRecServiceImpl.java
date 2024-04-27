package com.mindway.server.v2.domain.rec.service.impl;

import com.mindway.server.v2.domain.order.exception.NotAccessStudentException;
import com.mindway.server.v2.domain.rec.entity.Rec;
import com.mindway.server.v2.domain.rec.execption.NotFoundRecommendException;
import com.mindway.server.v2.domain.rec.repository.RecRepository;
import com.mindway.server.v2.domain.rec.service.DeleteRecService;
import com.mindway.server.v2.domain.user.entity.Authority;
import com.mindway.server.v2.domain.user.entity.User;
import com.mindway.server.v2.domain.user.util.UserUtil;
import com.mindway.server.v2.global.annotation.ServiceWithTransaction;
import lombok.RequiredArgsConstructor;

@ServiceWithTransaction
@RequiredArgsConstructor
public class DeleteRecServiceImpl implements DeleteRecService {

    private final RecRepository recRepository;
    private UserUtil userUtil;

    public void execute(Long id) {
        User user = userUtil.getCurrentUser();

        if (user.getAuthority() == Authority.ROLE_STUDENT){
            throw new NotAccessStudentException();
        }

        Rec rec = recRepository.findById(id)
                .orElseThrow(NotFoundRecommendException::new);

        recRepository.delete(rec);
    }
}
