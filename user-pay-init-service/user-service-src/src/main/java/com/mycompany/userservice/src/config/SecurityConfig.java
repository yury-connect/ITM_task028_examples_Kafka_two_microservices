package com.mycompany.userservice.src.config;

import com.mycompany.userservice.src.config.security.CustomAccessDeniedHandler;
import com.mycompany.userservice.src.config.security.CustomAuthenticationEntryPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http,
                                                   CustomAuthenticationEntryPoint authenticationEntryPoint,
                                                   CustomAccessDeniedHandler accessDeniedHandler
    ) throws Exception {
        http
                .csrf().disable() // отключаем CSRF для POST-запросов без токенов.exceptionHandling()
                .exceptionHandling()
                    .authenticationEntryPoint(authenticationEntryPoint)  // 👈 обработка 401
                .accessDeniedHandler(accessDeniedHandler)  // 👈 обработка 403
                .and()
                .authorizeHttpRequests(authz -> authz
                        .anyRequest().authenticated()
                )
                .httpBasic(); // включаем базовую HTTP авторизацию

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        // Создаём пользователя в памяти (логин: user, пароль: 1234)
        return new InMemoryUserDetailsManager(
                User.withUsername("user")
                        .password("1234")
                        .roles("USER")
                        .build()
        );
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // NoOp — пароли без шифрования (для теста)
        return NoOpPasswordEncoder.getInstance();
    }
}
