package org.example.respository;

import org.example.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

//dao
//자동으로 bean 등록
//Repository 생략가능
public interface UserRepository extends JpaRepository <User, Integer>{
    // SELECT * FROM user WHERE username = 1?;
        Optional<User> findByUsername(String username);
}


//JPA Naming 쿼리_내가 만들어줌 쿼리 아래와 같이!
//SELECT *FROM user WHERE username=? AND password=?;
//    User findByUsernameAndPassword(String username, String password);