package com.mindway.server.v2.global.security.config;

import com.mindway.server.v2.domain.user.entity.Authority;
import com.mindway.server.v2.global.security.filter.JwtFilter;
import com.mindway.server.v2.global.security.handler.JwtAccessDeniedHandler;
import com.mindway.server.v2.global.security.handler.JwtAuthenticationEntryPoint;
import com.mindway.server.v2.global.security.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.filter.CorsFilter;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {
    private final JwtProvider jwtProvider;
    private final CorsFilter corsFilter;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .addFilterBefore(corsFilter, UsernamePasswordAuthenticationFilter.class)

                .exceptionHandling(exceptionConfig ->
                        exceptionConfig.authenticationEntryPoint(jwtAuthenticationEntryPoint)
                                .accessDeniedHandler(jwtAccessDeniedHandler)
                )

                .sessionManagement((sessionManagement) ->
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )

                .authorizeHttpRequests((authorizeRequests) ->
                        authorizeRequests
                                .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()

                                // auth
                                .requestMatchers(HttpMethod.POST, "/api/v2/auth/signin").permitAll()
                                .requestMatchers(HttpMethod.PATCH, "/api/v2/auth").permitAll()
                                .requestMatchers(HttpMethod.DELETE, "/api/v2/auth").permitAll()

                                // user
                                .requestMatchers(HttpMethod.GET, "/api/v2/my").authenticated()

                                // orders
                                .requestMatchers(HttpMethod.POST, "/api/v2/order").authenticated()
                                .requestMatchers(HttpMethod.DELETE, "/api/v2/order/{order_id}").authenticated()
                                .requestMatchers(HttpMethod.PATCH, "/api/v2/order/{order_id}").authenticated()
                                .requestMatchers(HttpMethod.GET, "/api/v2/order").authenticated()

                                // goal
                                .requestMatchers(HttpMethod.POST, "/api/v2/goal").authenticated()
                                .requestMatchers(HttpMethod.GET, "/api/v2/goal").authenticated()

                                // book
                                .requestMatchers(HttpMethod.POST, "/api/v2/book").authenticated()
                                .requestMatchers(HttpMethod.DELETE, "/api/v2/book/{book_id}").authenticated()
                                .requestMatchers(HttpMethod.PATCH, "/api/v2/book/{book_id}").authenticated()
                                .requestMatchers(HttpMethod.GET, "/api/v2/book/{book_id}").authenticated()
                                .requestMatchers(HttpMethod.GET, "/api/v2/book").authenticated()

                                // notice
                                .requestMatchers(HttpMethod.POST, "/api/v2/notice").hasAnyAuthority(Authority.ROLE_TEACHER.name(), Authority.ROLE_HELPER.name())
                                .requestMatchers(HttpMethod.GET, "/api/v2/notice").authenticated()

                                // event
                                .requestMatchers(HttpMethod.POST, "/api/v2/event").hasAnyAuthority(Authority.ROLE_TEACHER.name(), Authority.ROLE_HELPER.name())
                                .requestMatchers(HttpMethod.GET, "/api/v2/event").authenticated()
                                .requestMatchers(HttpMethod.GET, "/api/v2/event/{event_id}").authenticated()
                                .requestMatchers(HttpMethod.DELETE, "/api/v2/event/{event_id}").hasAnyAuthority(Authority.ROLE_TEACHER.name(), Authority.ROLE_HELPER.name())
                                .requestMatchers(HttpMethod.GET, "/api/v2/event/date").authenticated()

                                // rank
                                .requestMatchers(HttpMethod.GET, "/api/v2/rank").authenticated()

                                // recommend
                                .requestMatchers(HttpMethod.POST, "/api/v2/recommend").hasAnyAuthority(Authority.ROLE_TEACHER.name(), Authority.ROLE_HELPER.name())
                                .requestMatchers(HttpMethod.PATCH, "/api/v2/recommend/{rec_id}").hasAnyAuthority(Authority.ROLE_TEACHER.name(), Authority.ROLE_HELPER.name())
                                .requestMatchers(HttpMethod.GET, "/api/v2/recommend").authenticated()

                                .anyRequest().authenticated()
                )

                .addFilterBefore(new JwtFilter(jwtProvider), UsernamePasswordAuthenticationFilter.class);


        return http.build();

    }
}
