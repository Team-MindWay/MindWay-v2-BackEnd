package com.mindway.server.v2.global.gauth;

import gauth.GAuth;
import gauth.impl.GAuthImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GAuthConfig {

    @Bean
    public GAuth gauth() {
        return new GAuthImpl();
    }
}
