package com.avadamedia.USAINUA.config;

import com.avadamedia.USAINUA.jwt.JwtTokenFilter;
import com.avadamedia.USAINUA.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtTokenProvider jwtTokenProvider;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf().disable()
                .cors()
                .and()
                .httpBasic().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/get-password/**").permitAll();
                    auth.requestMatchers("/refresh/**").permitAll();
                    auth.requestMatchers("/login/**").permitAll();
                    auth.requestMatchers("/v3/api-docs/**").permitAll();
                    auth.requestMatchers("/").permitAll();
                    auth.requestMatchers("/approximate-price/**").authenticated();
                    auth.requestMatchers("/orders/**").authenticated();
                    auth.requestMatchers("/products/**").authenticated();
                    auth.requestMatchers("/shops/**").authenticated();
                    auth.requestMatchers("/user/**").authenticated();
                    auth.requestMatchers("/swagger-ui/**").permitAll();
                })
                .addFilterBefore(new JwtTokenFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling()
                .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED));
        return httpSecurity.build();
    }

}
