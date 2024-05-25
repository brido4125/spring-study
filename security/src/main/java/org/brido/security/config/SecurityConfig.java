package org.brido.security.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;


@EnableWebSecurity
@Configuration
public class SecurityConfig {

  /*
  * 아래의 등록된 빈으로 SecurityFilterChain이 사용되기 때문에, Default SecurityFilterChain는 사용되지 않는다.
  * */
  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
    httpSecurity
            .authorizeHttpRequests(auth -> auth.anyRequest().authenticated())
            .formLogin(form -> {
              form.loginPage("/loginPage")
                  .loginProcessingUrl("/loginProc")
                  .defaultSuccessUrl("/", true)
                  // true면 무조건 defaultSuccessUrl로 이동,
                  // false이면 인증을 요청했던 그 경로로 이동 /home에서 인증 요청을 하면 /home으로 보낸다. -> 이전 위치로 리다이렉트
                  .failureUrl("/failed")
                  .usernameParameter("userId")
                  .passwordParameter("passwd")
                  //Handler가 앞서 설정된 값들보다 우선순위가 높다
                  .successHandler((request, response, authentication) -> {
                    System.out.println("authentication = " + authentication);
                    response.sendRedirect("/home");
                  })
                  .failureHandler((request, response, exception) -> {
                    System.out.println("exception = " + exception.getMessage());
                    response.sendRedirect("/login");
                  })
                  .permitAll();
            });
    return httpSecurity.build();
  }

  @Bean
  public UserDetailsService userDetailsService() {
    UserDetails user = User.withUsername("Brido")
            .password("{noop}1111")
            .roles("USER")
            .build();
    return new InMemoryUserDetailsManager(user);
  }
}
