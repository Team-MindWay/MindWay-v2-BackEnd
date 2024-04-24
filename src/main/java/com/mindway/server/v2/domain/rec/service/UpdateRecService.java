package com.mindway.server.v2.domain.rec.service;

import com.mindway.server.v2.domain.rec.presentation.dto.reqest.UpdateRecRequest;

public interface UpdateRecService {
    void execute(Long id, UpdateRecRequest updateRecRequest);
}
