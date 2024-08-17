package org.example.service;

import org.example.model.User;
import org.example.respository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// 스프링이 컴포넌트 스캔을 통해 bean에 등록해줌(ioc해줌).
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public int 회원가입(User user){
       try{
            userRepository.save(user);
           return 1; //정상
       } catch (Exception e){
           e.printStackTrace();
           System.out.println("UserService:회원가입() : " + e.getMessage());
       }
       return -1; // 비정상


    }
}
