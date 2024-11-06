package org.brido.oauth2session.config;

import lombok.RequiredArgsConstructor;
import org.brido.oauth2session.service.CustomOAuth2UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {

  private final CustomOAuth2UserService customOAuth2UserService;

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
            .csrf((csrf) -> csrf.disable());

    http
            .formLogin((login) -> login.disable());

    http
            .httpBasic((basic) -> basic.disable());

    http
            .oauth2Login((oauth2) -> oauth2
                    .userInfoEndpoint((userInfo) -> userInfo
                            .userService(this.customOAuth2UserService)
                    )
            );

    http
            .authorizeHttpRequests((auth) -> auth
                    .requestMatchers("/", "/oauth2/**", "/login/**").permitAll()
                    .anyRequest().authenticated());

    return http.build();
  }
}
