package com.mindway.server.v2.domain.auth.presentation;

import com.mindway.server.v2.domain.auth.presentation.dto.request.SignInRequest;
import com.mindway.server.v2.domain.auth.presentation.dto.response.TokenResponse;
import com.mindway.server.v2.domain.auth.service.LogoutService;
import com.mindway.server.v2.domain.auth.service.ReissueTokenService;
import com.mindway.server.v2.domain.auth.service.SignInService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v2/auth")
@RequiredArgsConstructor
public class AuthController {

    private final SignInService signInService;
    private final ReissueTokenService reissueTokenService;
    private final LogoutService logoutService;

    @PostMapping("/signin")
    public ResponseEntity<TokenResponse> signIn(@RequestBody @Valid SignInRequest signInRequest) {
        TokenResponse response = signInService.execute(signInRequest);
        return ResponseEntity.ok(response);
    }

    @PatchMapping
    public ResponseEntity<TokenResponse> reissueToken(@RequestHeader String refreshToken) {
        TokenResponse tokenResponse = reissueTokenService.execute(refreshToken);
        return ResponseEntity.ok(tokenResponse);
    }

    @DeleteMapping
    public ResponseEntity<Void> logout() {
        logoutService.execute();
        return ResponseEntity.noContent().build();
    }

}
