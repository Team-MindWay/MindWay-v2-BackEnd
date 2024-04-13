package com.mindway.server.v2.domain.goal.presentation;

import com.mindway.server.v2.domain.goal.presentation.dto.request.GoalAddRequestDto;
import com.mindway.server.v2.domain.goal.service.GoalAddService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2/goal")
public class GoalController {

    private final GoalAddService goalAddService;

    @PostMapping
    public ResponseEntity<Void> addGoal(@Valid @RequestBody GoalAddRequestDto goalAddRequestDto) {
        goalAddService.execute(goalAddRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


}
