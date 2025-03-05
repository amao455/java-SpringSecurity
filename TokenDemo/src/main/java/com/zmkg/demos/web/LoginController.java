package com.zmkg.demos.web;


import com.zmkg.demos.domain.ResponseResult;
import com.zmkg.demos.domain.User;
import com.zmkg.demos.service.LoginService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import javax.annotation.Resource;

@RestController
public class LoginController {


    @Resource
    private LoginService loginService;


    @PostMapping("/user/login")
    public ResponseResult login(@RequestBody User user){
        System.out.println("正在登录");
        // 登录
        return loginService.login(user);

    }

    @RequestMapping("/user/logout")
    public ResponseResult logout(){
        return loginService.logout();
    }

}
