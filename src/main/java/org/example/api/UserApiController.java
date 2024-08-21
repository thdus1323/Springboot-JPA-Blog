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

    @PostMapping("/auth/joinProc")
    public ResponseDto<Integer> save(@RequestBody User user) { //요청받는 게 json이니까 @Requestbody, user에는 pw, email, username이 담김. user class 중 role이 없음
        System.out.println("UserApiController : save 호출됨"); // 호출이 잘 되는 지 확인
        user.setRole(RoleType.USER); // 그래서 우리가 role 강제 주자.
        userService.회원가입(user);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

//    @PostMapping("/api/user/login")
//    public ResponseDto<Integer> login(@RequestBody User user, Model model, HttpSession session) {
//        System.out.println("UserApiController : login 호출됨"); // 호출이 잘 되는 지 확인
//        User principal = userService.로그인(user); // principal(접근주체)
//
//        if (principal != null) {
//            session.setAttribute("principal", principal);
//        }
//        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
//    }

}
