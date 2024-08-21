package org.example.config.auth;

import org.example.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

// 스프링 시큐리티가 로그인 요청을 가로채서 로그인을 진행하고 완료가 되면
// UserDetails 타입의 오브젝트를 스프링 시큐리티의 고유한 세션 저장소에 저장을 해준다.
public class PrincipalDetail implements UserDetails {
    private User user; //컴퍼지션

    public PrincipalDetail(User user){
        this.user = user;
    }
    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    //계정 만료안됨
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    //계정 잠기지 않음
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    //비밀번호 만료안됨
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    //계정 활성화됨(사용가능)
    @Override
    public boolean isEnabled() {
        return true;
    }



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList((GrantedAuthority) () -> "ROLE_" + user.getRole());
    }

}
