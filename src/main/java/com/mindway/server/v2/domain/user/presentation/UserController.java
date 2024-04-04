package com.mindway.server.v2.domain.user.presentation;

import com.mindway.server.v2.domain.user.presentation.dto.response.UserInfoResponse;
import com.mindway.server.v2.domain.user.service.UserInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2/my")
public class UserController {

    private final UserInfoService userInfoService;

    @GetMapping
    public ResponseEntity<UserInfoResponse> getUserInfo() {
        UserInfoResponse userInfoResponse = userInfoService.execute();
        return ResponseEntity.ok(userInfoResponse);
    }

}
