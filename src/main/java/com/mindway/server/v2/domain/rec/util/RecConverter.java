package com.mindway.server.v2.domain.rec.util;

import com.mindway.server.v2.domain.rec.entity.Rec;
import com.mindway.server.v2.domain.rec.entity.Type;
import com.mindway.server.v2.domain.rec.presentation.dto.reqest.WriteRecRequest;
import com.mindway.server.v2.domain.rec.presentation.dto.response.RecInfoResponse;

public interface RecConverter {
    Rec toEntity (WriteRecRequest writeRecRequest, Type type);

    RecInfoResponse toDto(Rec rec);
}
