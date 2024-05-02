package com.mindway.server.v2.global.security.jwt;

import com.mindway.server.v2.domain.auth.presentation.dto.response.TokenResponse;
import com.mindway.server.v2.global.auth.AuthDetailsService;
import com.mindway.server.v2.global.exception.ErrorCode;
import com.mindway.server.v2.global.exception.MindWayException;
import com.mindway.server.v2.global.redis.RedisUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.security.Key;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

import static com.mindway.server.v2.global.security.filter.JwtFilter.AUTHORIZATION_HEADER;
import static com.mindway.server.v2.global.security.filter.JwtFilter.BEARER_PREFIX;


@Component
@RequiredArgsConstructor
public class JwtProvider {
    private static final String AUTHORITIES_KEY = "auth";
    private static final String BEARER_TYPE = "Bearer ";
    private static final long ACCESS_TOKEN_TIME = 1000 * 60 * 30L;
    private static final long REFRESH_TOKEN_TIME = 1000 * 60 * 60 * 24 * 7L;

    @Value("${jwt.secret}")
    private String secretKey;
    private static Key key;
    private final AuthDetailsService authDetailsService;
    private final RedisUtil redisUtil;

    @PostConstruct
    public void init(){
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        key = Keys.hmacShaKeyFor(keyBytes);
    }

    public TokenResponse generateTokenDto(UUID id) {
        return TokenResponse.builder()
                .accessToken(generateAccessToken(id))
                .refreshToken(generateRefreshToken(id))
                .accessTokenExpiresIn(LocalDateTime.now().plusSeconds(ACCESS_TOKEN_TIME))
                .refreshTokenExpiresIn(LocalDateTime.now().plusSeconds(REFRESH_TOKEN_TIME))
                .build();
    }

    public Long getExpiration(String accessToken) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(accessToken)
                .getBody();

        return claims.getExpiration().getTime();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);

            return !redisUtil.hasKeyBlackList(token);
        } catch (ExpiredJwtException e) {
            throw new MindWayException(ErrorCode.EXPIRED_TOKEN);
        } catch (Exception e) {
            throw new MindWayException(ErrorCode.INVALID_TOKEN_TYPE);

        }
    }

    public Authentication getAuthentication(String accessToken) {
        Claims claims = parseClaims(accessToken);

        if (claims.get(AUTHORITIES_KEY) == null) {
            throw new MindWayException(ErrorCode.INVALID_TOKEN);
        }

        UserDetails principal = authDetailsService.loadUserByUsername(claims.getSubject());
        return new UsernamePasswordAuthenticationToken(principal, "", principal.getAuthorities());
    }

    private Claims parseClaims(String accessToken) {
        try {
            return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(accessToken).getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }

    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(BEARER_PREFIX)) {
            return bearerToken.substring(7);
        }
        return null;
    }

    public String parseRefreshToken(String refreshToken) {
        if (refreshToken.startsWith(BEARER_TYPE)) {
            return refreshToken.replace(BEARER_TYPE, "");
        } else {
            return null;
        }
    }

    public String generateAccessToken(UUID id) {
        long now = (new Date()).getTime();

        Date accessTokenExpiresIn = new Date(now + ACCESS_TOKEN_TIME);

        return Jwts.builder()
                .setSubject(id.toString())
                .claim(AUTHORITIES_KEY, "JWT")
                .setIssuedAt(new Date())
                .setExpiration(accessTokenExpiresIn)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public String generateRefreshToken(UUID id) {
        long now = (new Date()).getTime();

        Date refreshTokenExpiresIn = new Date(now + REFRESH_TOKEN_TIME);

        return Jwts.builder()
                .setSubject(id.toString())
                .setExpiration(refreshTokenExpiresIn)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }
}
