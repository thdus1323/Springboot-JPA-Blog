package org.example.config.auth;

import org.example.model.User;
import org.example.respository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service // Bean등록
public class PrincipalDetailService  implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    // 스프링이 로그인 요청을 가로챌 때, username, password 변수 2개를 가로채는데
    //password 부분 처리는 알아서 함
    //username이 db에 잇는지만 확인해주면 됨.
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        User principal = userRepository.findByUsername(username)
                .orElseThrow(()->{
                    return new UsernameNotFoundException("해당 사용자를 찾을 수 없습니다: " + username);
                });
        return new PrincipalDetail(principal); //시큐리티의 세션에 유저 정보가 저장이 됨. 아이디 : user, 패스워드: 콘솔창
    }
}
