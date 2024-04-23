package com.mindway.server.v2.domain.rec.presentation;

import com.mindway.server.v2.domain.rec.entity.Type;
import com.mindway.server.v2.domain.rec.presentation.dto.reqest.WriteRecRequest;
import com.mindway.server.v2.domain.rec.service.WriteRecService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2/recommend")
public class RecController {
    private final WriteRecService writeRecService;

    @PostMapping
    public ResponseEntity<Void> writeRecBook (@RequestBody @Valid WriteRecRequest writeRecRequest, @RequestParam Type type){
        writeRecService.execute(writeRecRequest, type);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
