package org.example.ex01;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TempControllerTest {

    //야물 설정으로 이 주소는 http://localhost:8000/blog/temp/home
    @GetMapping("/temp/home")
    public String tempHome(){
        System.out.println("tempHome()");
        //파일리턴 기본 경로 : src/main/resource/static/home.html
        return "/home.html";
    }

    @GetMapping("/temp/img")
    public String tempImg(){
        return "/git_고양이.webp";
    }

    //야물 설정으로 이 주소는 http://localhost:8000/blog/temp/jsp
    @GetMapping("/temp/jsp")
    public String tempJsp(){
        //리턴시 앞에는 : /WEP-INF/views/ 붙고
        // 리턴 뒤에는 : .jsp가 붙음
        return "test";
    }
}
