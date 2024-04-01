package com.mindway.server.v2.domain.user.util;

import com.mindway.server.v2.domain.auth.exception.MemberNotFoundException;
import com.mindway.server.v2.domain.user.entity.User;
import com.mindway.server.v2.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserUtil {

    private final UserRepository userRepository;

    public User getCurrentUtil() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByEmail(email)
                .orElseThrow(MemberNotFoundException::new);
    }
}
