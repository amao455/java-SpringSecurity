package com.zmkg.demos.service.impl;

import com.zmkg.demos.domain.LoginUser;
import com.zmkg.demos.domain.ResponseResult;
import com.zmkg.demos.domain.User;
import com.zmkg.demos.service.LoginService;


import com.zmkg.demos.utils.JwtUtil;
import com.zmkg.demos.utils.RedisCache;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


@Service
public class LoginServiceImpl implements LoginService {

    @Resource
    private RedisCache redisCache;


    @Resource
    private AuthenticationManager authenticationManager;

    @Override
    public ResponseResult login(User user) {
        // AuthenticationManager.authenticate()进行认证
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);


        // 如果没有通过认证，给出对应的提示
        if(Objects.isNull(authenticate)){
            throw new RuntimeException("登录失败");
        }

        // 如果认证通过了，使用userId生成一个jwt jwt存入ResponseResult返回
        LoginUser loginUser = (LoginUser)authenticate.getPrincipal();
        String userId = loginUser.getUser().getId().toString();
        String jwt = JwtUtil.createJWT(userId);

        Map<String, String> map = new HashMap<>();
        map.put("token", jwt);

        // 把完整的用户信息存入redis，userId作为key
        redisCache.setCacheObject("login:" + userId, loginUser);
        return new ResponseResult(200, "登录成功", map);
    }

    @Override
    public ResponseResult logout() {
         // 获取SecurityContextHolder中的用户id
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser)authentication.getPrincipal();
        Long userId = loginUser.getUser().getId();

        // 删除redis中的值
        redisCache.deleteObject("login:" + userId);

        return new ResponseResult(200, "注销成功");
    }
}
