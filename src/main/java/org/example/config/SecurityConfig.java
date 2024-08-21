package org.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    @Bean //IOC가 된다.
    public BCryptPasswordEncoder encodePWD(){
        return new BCryptPasswordEncoder();
    }

    @Bean // IOC가 된다.
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) //csrf토큰 비활성화(테스트시 걸어주는 게 좋다)
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/auth/**", "/js/**", "/css/**", "/image/**", "/").permitAll()  // /auth/** 경로는 인증 없이 접근 허용
                        .anyRequest().authenticated()  // 그 외의 모든 요청은 인증 필요
                )
                .formLogin(withDefaults());  // 기본 로그인 폼 사용
        return http.build();
    }
}
