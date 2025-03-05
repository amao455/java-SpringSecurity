package com.zmkg.demos.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zmkg.demos.domain.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
