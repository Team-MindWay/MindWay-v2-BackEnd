package com.mindway.server.v2.domain.rec.presentation;

import com.mindway.server.v2.domain.rec.entity.Type;
import com.mindway.server.v2.domain.rec.presentation.dto.reqest.UpdateRecRequest;
import com.mindway.server.v2.domain.rec.presentation.dto.reqest.WriteRecRequest;
import com.mindway.server.v2.domain.rec.presentation.dto.response.RecInfoResponse;
import com.mindway.server.v2.domain.rec.service.GetRecService;
import com.mindway.server.v2.domain.rec.service.UpdateRecService;
import com.mindway.server.v2.domain.rec.service.WriteRecService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2/recommend")
public class RecController {
    private final WriteRecService writeRecService;
    private final GetRecService getRecService;
    private final UpdateRecService updateRecService;

    @PostMapping
    public ResponseEntity<Void> writeRecBook (@RequestBody @Valid WriteRecRequest writeRecRequest, @RequestParam Type type){
        writeRecService.execute(writeRecRequest, type);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<RecInfoResponse>> getRecBooks (@RequestParam Type type) {
        List<RecInfoResponse> responses = getRecService.execute(type);
        return ResponseEntity.ok(responses);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateRecBook (@PathVariable Long id, @RequestBody @Valid UpdateRecRequest updateRecRequest) {
        updateRecService.execute(id, updateRecRequest);
        return ResponseEntity.noContent().build();
    }

}
