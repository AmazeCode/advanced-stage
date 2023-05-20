package com.ac.springboot.mapper;

import com.ac.springboot.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * @Description: 用户映射DAO
 * @author: zhangyadong
 * @Date: 2019/11/18 0018 下午 4:34
 * @version: V1.0
 */
@Repository
public interface UserMapper extends BaseMapper<User> {
}
