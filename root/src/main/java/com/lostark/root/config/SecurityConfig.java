package com.lostark.root.config;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .cors((corsCustomizer -> corsCustomizer.configurationSource(new CorsConfigurationSource() {

                    @Override
                    public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {

                        CorsConfiguration config = new CorsConfiguration();

                        config.setAllowedOrigins(List.of("http://localhost:3000", "https://lostroot.shop", "http://221.145.113.28/"));
                        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"));
                        config.setAllowCredentials(true);
                        config.setAllowedHeaders(List.of("*"));
                        config.setMaxAge(3600L);

                        return config;
                    }
                })));

        //csrf disable
        http
                .csrf((auth) -> auth.disable());

        //From 로그인 방식 disable
        http
                .formLogin((auth) -> auth.disable());

        //http basic 인증 방식 disable
        http
                .httpBasic((auth) -> auth.disable());


        //경로별 인가 작업
        http
                .authorizeHttpRequests((auth) -> auth

                        // 개발용 페이지
                        .requestMatchers("/swagger", "/swagger-ui.html", "/swagger-ui/**", "/api-docs", "/api-docs/**", "/v3/api-docs/**")
                        .permitAll()

                        //test
                        .requestMatchers(HttpMethod.GET, "/test/**").permitAll()

                        //board
                        .requestMatchers(HttpMethod.GET, "/board/notice/*", "/board/notice/**").permitAll()
//                        .requestMatchers(HttpMethod.POST, "/board/notice/**").permitAll()

                        .requestMatchers(HttpMethod.GET, "/board/mini/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/board/mini/**").permitAll()

                        //auction
                        .requestMatchers(HttpMethod.GET, "/auction/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auction/**").permitAll()

                        .requestMatchers("/*", "/**").denyAll()
                        .anyRequest().authenticated());
//                .exceptionHandling((exceptionConfig) ->
//                        exceptionConfig.authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/user/error")));


        return http.build();
    }
}
