package org.example.ex01;

import org.example.model.RoleType;
import org.example.model.User;
import org.example.respository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DummyControllerTest {

    @Autowired //DI
    private UserRepository userRepository;

    //http://localhost:8000/blog/dummy/join(요청)
    //http의 body에 username, password,email 데이터를 가지고 (요청)
    @PostMapping("/dummy/join")
    public String join(User user){
        System.out.println("userId = " + user.getId());
        System.out.println("username = " + user.getUsername());
        System.out.println("password = " + user.getPassword());
        System.out.println("email = " + user.getEmail());
        System.out.println("role = " + user.getRole());
        System.out.println("createDate = " + user.getCreateDate());

        user.setRole(RoleType.USER);
        userRepository.save(user); //save는 insert,update다 들고 있음
        return "회원가입이 완료되었어요!";


    }
}
