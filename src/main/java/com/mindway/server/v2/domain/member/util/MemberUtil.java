package com.mindway.server.v2.domain.member.util;

import com.mindway.server.v2.domain.auth.exception.MemberNotFoundException;
import com.mindway.server.v2.domain.member.entity.Member;
import com.mindway.server.v2.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberUtil {

    private final MemberRepository memberRepository;

    public Member getCurrentMember() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return memberRepository.findByEmail(email)
                .orElseThrow(MemberNotFoundException::new);
    }
}
