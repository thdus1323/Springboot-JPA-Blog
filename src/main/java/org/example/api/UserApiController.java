package org.example.api;

import jakarta.servlet.http.HttpSession;
import org.example.dto.ResponseDto;
import org.example.model.RoleType;
import org.example.model.User;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserApiController { // 이후 앱에도 쓸 수 있음

    @Autowired
    private UserService userService;

    @Autowired
    private HttpSession session;

    @PostMapping("/api/user")
    public ResponseDto<Integer> save(@RequestBody User user){ //요청받는 게 json이니까 @Requestbody, user에는 pw, email, username이 담김. user class 중 role이 없음
        System.out.println("UserApiController : save 호출됨"); // 호출이 잘 되는 지 확인
        user.setRole(RoleType.USER); // 그래서 우리가 role 강제 주자.
       userService.회원가입(user);
        return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
    }

    @PostMapping("/api/user/login")
    public ResponseDto<Integer> login(@RequestBody User user, Model model){
        System.out.println("UserApiController : login 호출됨"); // 호출이 잘 되는 지 확인
        User principal = userService.로그인(user); // principal(접근주체)
//        userService.로그인(user);
//        return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
       // System.out.println("principal = " + principal);

        if (principal != null){
            session.setAttribute("principal", principal);
            System.out.println("principal = 잘 받아왔니? " + principal);
//             model.addAttribute("principal",principal);
//            System.out.println("model = model 잘 들어왔니? " + model);
            return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
        }else {
            return new ResponseDto<Integer>(HttpStatus.UNAUTHORIZED.value(),-1);
        }

    }

    @PostMapping("/user/logout")
    public void logout(){
        session.invalidate();
    }

}
