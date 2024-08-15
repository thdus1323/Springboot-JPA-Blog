package org.example.ex01;

import org.example.model.RoleType;
import org.example.model.User;
import org.example.respository.UserRepository;
import org.hibernate.boot.model.naming.IllegalIdentifierException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.theme.CookieThemeResolver;

import java.util.function.Supplier;

@RestController
public class DummyControllerTest {

    @Autowired //DI
    private UserRepository userRepository;

    //{id} 주소로 파라미터를 전달 받을 수 있다.
    //http://localhost:8000/blog/dummy/user/3
    @GetMapping("/dummy/user/{id}")
    public User detail(@PathVariable int id) {
        // 만약 user/4 를 찾으면 db에서 못 찾잖아. 없으니까. user가  null이잖아
        //그럼 null이 return 되니까 프로그램에 문제가 생김
        // 그래서 optional으로 너의 user 객체를 감싸서 가져올테니까 null인지 아닌지 판단해서 return 해
        //람다식
//        User user = userRepository.findById(id).orElseThrow(()->{
//            return new IllegalArgumentException("해당 사용자는 없습니다.");
//        });
        User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalIdentifierException>() {
            @Override
            public IllegalIdentifierException get() {
                return new IllegalIdentifierException("해당 유저는 없습니다. id : " + id);
            }
        });
        //user 객체 = 자바 오브젝트
        //웹브라우저가 요청하는 것인데 자바오브젝트를 반환하면 이해 x
        //그래서 웹 브라우저가 이해할 수 있도록 json(옛날에는 gson lib를 활용하여 변환)으로 변환시켜야 함.
        //지금은 스프링부트에서 MessageConverter가 응답 시에 자동 작동
        //만약 자바 오브젝트를 리턴하게 되면 MessageConverter가 Jackson lib를 호출해서
        //user 오브젝트를 json으로 변환해서 브라우저에게 던져 줌.
        return user;
    }

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
