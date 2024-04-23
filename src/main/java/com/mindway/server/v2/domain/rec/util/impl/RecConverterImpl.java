package com.mindway.server.v2.domain.rec.util.impl;

import com.mindway.server.v2.domain.rec.entity.Rec;
import com.mindway.server.v2.domain.rec.entity.Type;
import com.mindway.server.v2.domain.rec.presentation.dto.reqest.WriteRecRequest;
import com.mindway.server.v2.domain.rec.util.RecConverter;
import org.springframework.stereotype.Component;

@Component
public class RecConverterImpl implements RecConverter {

    public Rec toEntity(WriteRecRequest writeRecRequest, Type type) {
        return Rec.builder()
                .title(writeRecRequest.getTitle())
                .content(writeRecRequest.getContent())
                .author(writeRecRequest.getAuthor())
                .type(type)
                .build();
    }
}
