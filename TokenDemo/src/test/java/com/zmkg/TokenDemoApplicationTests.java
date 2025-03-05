package com.zmkg;

import com.zmkg.demos.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;

@SpringBootTest
class TokenDemoApplicationTests {

    @Resource
    private UserMapper userMapper;


    @Resource
    private PasswordEncoder passwordEncoder;

    @Test
    void contextLoads() {


        System.out.println(passwordEncoder.encode("1234"));
    }

}
