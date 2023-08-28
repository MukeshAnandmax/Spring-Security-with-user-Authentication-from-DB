package com.practice.springsecurityusingdb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @Autowired
    UserRepository userRepository;


    @GetMapping("/test")
    public  String test(){
        return "<h1>Spring security User DB testing,Anybody can access.........</h1>";
    }

    @GetMapping("/auth1")
    public  String test1(){
        return "<h1>Hello!! from auth1.........</h1>";
    }
    @GetMapping("/auth2")
    public  String test2(){
        return "<h1>Hello!! from auth2.........</h1>";
    }

    @PostMapping("/signup1")
    public  void  signup(@RequestBody MyUser user){

        user.setAuthorities("auth1");
        PasswordEncoder passwordEncoder= new BCryptPasswordEncoder();

        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepository.save(user);

    }

    @PostMapping("/signup2")
    public  void  signup2(@RequestBody MyUser user){

        user.setAuthorities("auth1:auth2");
        PasswordEncoder passwordEncoder= new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepository.save(user);

    }

}
