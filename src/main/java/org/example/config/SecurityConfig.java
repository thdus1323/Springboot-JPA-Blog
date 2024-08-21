package org.example.config;

import org.example.config.auth.PrincipalDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    @Autowired
    private PrincipalDetailService principalDetailService;

    @Bean //IOC가 된다.
    public BCryptPasswordEncoder encodePWD(){
        return new BCryptPasswordEncoder();
    }

//    @Bean // IOC가 된다.
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf(csrf -> csrf.disable()) //csrf토큰 비활성화(테스트시 걸어주는 게 좋다)
//                .authorizeHttpRequests(authorize -> authorize
//                        .requestMatchers("/auth/**", "/js/**", "/css/**", "/image/**", "/").permitAll()  // /auth/** 경로는 인증 없이 접근 허용
//                        .anyRequest().authenticated()  // 그 외의 모든 요청은 인증 필요
//                )
//                .formLogin(withDefaults());  // 기본 로그인 폼 사용
//        return http.build();
//
//
//    }


//시큐리티가 대신 로그인을 하고 그 때 password를 가로채기를 하는데
//해당 password가 무엇으로 해쉬가 되어 회원가입이 되었는지를 알아야
//같은 해쉬로 암호화해서 db에 있는 해쉬랑 비교할 수 있다.
   // @Override
   // protected void configure(AuthenticationManagerBuilder auth) throws Exception{
    //    auth.userDetailsService(null).passwordEncoder(encodePWD());
   // }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())  // CSRF 보호 비활성화
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/","/js/**", "/css/**", "image/**").permitAll()  // 루트 경로에 대해 모든 사용자 접근 허용
                        .anyRequest().authenticated()  // 나머지 요청에 대해 인증 요구
                )
                .formLogin(form -> form
                        .loginPage("/auth/loginForm")  // 사용자 정의 로그인 페이지 설정
                        .loginProcessingUrl("/auth/loginProc")  // 로그인 처리 URL 설정
                );

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, PasswordEncoder passwordEncoder, UserDetailsService userDetailsService) throws Exception {
        AuthenticationManagerBuilder auth = http.getSharedObject(AuthenticationManagerBuilder.class);
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
        return auth.build();
    }
}
