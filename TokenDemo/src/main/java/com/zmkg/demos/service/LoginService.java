package com.zmkg.demos.service;

import com.zmkg.demos.domain.ResponseResult;
import com.zmkg.demos.domain.User;


public interface LoginService {
    ResponseResult login(User user);
}
