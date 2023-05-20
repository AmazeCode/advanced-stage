package com.ac.jpa.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 *  @Description: 用户实体
 *  @author: zhangyadong
 *  @Date: 2020/7/26 18:09
 *  @version: V1.0
 */
@Data
@Entity
public class User {

    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    private String email;

    private int age;

    @Column(name="birth_day")
    private Date birthDay;
}
