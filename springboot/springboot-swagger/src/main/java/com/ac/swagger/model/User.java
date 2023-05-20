package com.ac.swagger.model;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;

/**
 *  @Description: 用户
 *  @author: zhangyadong
 *  @Date: 2020/7/25 19:17
 *  @version: V1.0
 */
@Data
public class User {

    @NotNull(message = "名字不能为空")
    private String name;

    @Range(min = 1,max = 120,message = "年龄要在1到120之间")
    private int age;

    @Email(message = "Email格式不正确")
    private String email;

    @Past(message = "生日必须为过去的时间")
    private LocalDate birthDay;
}
