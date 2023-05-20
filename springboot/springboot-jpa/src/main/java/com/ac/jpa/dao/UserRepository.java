package com.ac.jpa.dao;

import com.ac.jpa.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Description: 用户dao
 * @author: zhangyadong
 * @Date: 2020/7/26 18:13
 * @version: V1.0
 */
public interface UserRepository extends JpaRepository<User,Integer> {

    /**
     * @Description 通过年龄查找用户
     * @params [age]
     * @return java.util.List<com.ac.jpa.pojo.User>
     * @author zhangyadong
     * @date 2020/7/26 18:43
     */
    public List<User> findByAge(Integer age);
}
