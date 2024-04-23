package com.mindway.server.v2.domain.rec.service;

import com.mindway.server.v2.domain.rec.entity.Type;
import com.mindway.server.v2.domain.rec.presentation.dto.reqest.WriteRecRequest;

public interface WriteRecService {
    void execute(WriteRecRequest writeRecRequest, Type type);
}
