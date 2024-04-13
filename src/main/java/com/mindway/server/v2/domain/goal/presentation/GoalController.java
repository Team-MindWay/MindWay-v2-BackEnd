package com.mindway.server.v2.domain.goal.presentation;

import com.mindway.server.v2.domain.goal.presentation.dto.request.GoalAddRequestDto;
import com.mindway.server.v2.domain.goal.presentation.dto.response.GoalGetResponse;
import com.mindway.server.v2.domain.goal.service.GoalAddService;
import com.mindway.server.v2.domain.goal.service.GoalGetService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2/goal")
public class GoalController {

    private final GoalAddService goalAddService;
    private final GoalGetService goalGetService;

    @PostMapping
    public ResponseEntity<Void> addGoal(@Valid @RequestBody GoalAddRequestDto goalAddRequestDto) {
        goalAddService.execute(goalAddRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<GoalGetResponse> getGoal() {
        GoalGetResponse response = goalGetService.execute();
        return ResponseEntity.ok(response);
    }


}
