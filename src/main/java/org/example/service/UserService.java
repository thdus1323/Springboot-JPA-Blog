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

}
