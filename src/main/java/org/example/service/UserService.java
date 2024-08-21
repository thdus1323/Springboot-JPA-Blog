package org.example.service;

import org.example.model.User;
import org.example.respository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// 스프링이 컴포넌트 스캔을 통해 bean에 등록해줌(ioc해줌).
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void 회원가입(User user){
            userRepository.save(user);
    }

//import org.springframework.transaction.annotation.Transactional;
    @Transactional(readOnly = true) //select 할 때 트랜잭션 시작하고 서비스 종료시에 트랜잭션 종료(정합성)
    public User 로그인(User user) {
        System.out.println("user = " + user);
        return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
    }
}
