package com.fruits.token.controller;

import com.fruits.token.annotations.Auth;
import com.fruits.token.entity.User;
import com.fruits.token.service.UserService;
import com.fruits.token.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpServletResponse response){
        User user = userService.getUserByName(username);

        String token = null;
        if(user.getPassword().equals(password)){
            token = JwtUtil.createToken(user);
            response.setHeader("JWT-TOKEN", token);
        }

        return token;
    }


    @GetMapping("/testAuth")
    @Auth("")
    public String testAuth(){
        return "验证成功！";
    }


}
