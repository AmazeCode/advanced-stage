package com.ac.jpa.controller;

import com.ac.jpa.dao.UserRepository;
import com.ac.jpa.pojo.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *  @Description: 用户控制层
 *  @author: zhangyadong
 *  @Date: 2020/7/26 18:15
 *  @version: V1.0
 */
@Api(tags = "用户控制接口")
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @ApiOperation(value = "根据id获取用户信息")
    @GetMapping("/{id}")
    public User get(@PathVariable int id){
        //java 8写法
        return userRepository.findById(id).get();
    }

    @ApiOperation(value = "创建用户")
    @PostMapping("")
    public User create(@RequestBody User user) {
        return userRepository.save(user);
    }

    @ApiOperation(value = "更新用户")
    @PutMapping("")
    public User update(@RequestBody User user) {
        return userRepository.save(user);
    }

    @ApiOperation(value = "删除用户")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        userRepository.deleteById(id);
    }

    @ApiOperation(value = "根据年龄查询用户")
    @GetMapping("/age/{age}")
    public List<User> getUserByAge(@PathVariable Integer age){
        return userRepository.findByAge(age);
    }

    /**
     * @Description 分页操作(分页是从0页开始)
     * @params [property, direction, page, size]
     * @return org.springframework.data.domain.Page<com.ac.jpa.pojo.User>
     * @author zhangyadong
     * @date 2020/7/26 19:05
     */
    @ApiOperation(value = "分页获取用户列表")
    @GetMapping("")
    public Page<User> list(String property, String direction, Integer page, Integer size){

        Pageable pageable = PageRequest.of(page-1, size, Sort.Direction.fromString(direction),property);

        return userRepository.findAll(pageable);
    }
}
