package com.ismaildrcn.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.ismaildrcn.handler.AuthEntryPoint;
import com.ismaildrcn.jwt.JWTAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

        public static final String REGISTER = "/register";
        public static final String AUTHENTICATE = "/authenticate";
        public static final String REFRESH_TOKEN = "/refresh-token";

        public static final String[] SWAGGER_PATHS = {
                        "/swagger-ui/**",
                        "/swagger-ui.html",
                        "/v3/api-docs/**",
                        "/v3/api-docs"
        };

        @Autowired
        private AuthenticationProvider authenticationProvider;

        @Autowired
        private JWTAuthenticationFilter jwtAuthFilter;

        @Autowired
        private AuthEntryPoint authEntryPoint;

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
                http.csrf(csrf -> csrf.disable())
                                .authorizeHttpRequests(
                                                request -> request
                                                                .requestMatchers(REGISTER, AUTHENTICATE, REFRESH_TOKEN)
                                                                .permitAll()
                                                                .requestMatchers(SWAGGER_PATHS).permitAll()
                                                                .anyRequest().authenticated())
                                .exceptionHandling(ex -> ex.authenticationEntryPoint(authEntryPoint))
                                .sessionManagement(session -> session
                                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                                .authenticationProvider(authenticationProvider)
                                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
                return http.build();
        }

}
