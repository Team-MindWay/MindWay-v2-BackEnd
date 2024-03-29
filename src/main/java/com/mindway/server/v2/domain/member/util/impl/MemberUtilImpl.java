package com.mindway.server.v2.domain.member.util.impl;

import com.mindway.server.v2.domain.auth.exception.MemberNotFoundException;
import com.mindway.server.v2.domain.member.entity.Member;
import com.mindway.server.v2.domain.member.repository.MemberRepository;
import com.mindway.server.v2.domain.member.util.MemberUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class MemberUtilImpl implements MemberUtil {

    private final MemberRepository memberRepository;

    public Member getCurrentMember() {
        String id = SecurityContextHolder.getContext().getAuthentication().getName();
        return memberRepository.findById(UUID.fromString(id))
                .orElseThrow(MemberNotFoundException::new);
    }
}
