package com.mindway.server.v2.global.auth;

import com.mindway.server.v2.domain.member.repository.MemberRepository;
import com.mindway.server.v2.global.exception.ErrorCode;
import com.mindway.server.v2.global.exception.GlobalException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthDetailsService implements UserDetailsService {
    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return memberRepository.findByEmail(email)
                .map(AuthDetails::new)
                .orElseThrow(() -> new GlobalException(ErrorCode.MEMBER_NOT_FOUND));
    }
}
