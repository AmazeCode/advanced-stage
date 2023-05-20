package com.ac.springboot.feature.collector.controller;

import com.ac.springboot.feature.collector.bean.Employee;
import com.ac.springboot.feature.collector.service.CollectorService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @Author: zhangyadong
 * @Date: 2022/8/10 7:07
 */
@RestController
@RequestMapping("/collector")
public class CollectorController {

    @Resource
    private CollectorService collectorService;

    /**
     * @description: List<Employee>过滤返回过滤后的List<Employee>
     * @param:
     * @return: java.lang.Object
     * @author: zhangyadong
     * @date: 2022/8/10 7:13
     */
    @GetMapping("/filter")
    public Object filterEmployeeByCompany() {
        List<Employee> companys = collectorService.filterEmployeeByCompany("上海公司");
        return companys;
    }

    /**
     * @description: List<Employee转换为Map<String, List<Employee>>,根据某一字段分组
     * @param:
     * @return: java.lang.Object
     * @author: zhangyadong
     * @date: 2022/8/10 7:15
     */
    @GetMapping("/groupingBy")
    public Object filterEmployeesThenGroupByStream() {
        Map<String, List<Employee>> stringListMap = collectorService.filterEmployeesThenGroupByStream();
        return stringListMap;
    }

    /**
     * @description: 计算薪水和
     * @param:
     * @return: java.lang.Object
     * @author: zhangyadong
     * @date: 2022/8/10 7:47
     */
    @GetMapping("/sum")
    public Object calculateSum() {
        Integer salarySum = collectorService.calculateSum();
        return salarySum;
    }

    /**
     * @description: 查询最大薪水员工
     * @param:
     * @return: java.lang.Object
     * @author: zhangyadong
     * @date: 2022/8/10 7:47
     */
    @GetMapping("/max")
    public Object highestSalaryEmployee() {
        Employee employee = collectorService.highestSalaryEmployee();
        return employee;
    }

    @GetMapping("/myCollector")
    public Object myCollector() {
        Integer integer = collectorService.myCollector();
        return integer;
    }
}
