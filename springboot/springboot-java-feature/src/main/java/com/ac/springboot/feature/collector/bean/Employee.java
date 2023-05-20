package com.ac.springboot.feature.collector.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 员工类
 * @Author: zhangyadong
 * @Date: 2022/8/9 23:33
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Employee {

    /**
     * 姓名
     */
    private String name;

    /**
     * 子公司
     */
    private String subCompany;

    /**
     * 部门
     */
    private String department;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 薪水
     */
    private Integer salary;
}
