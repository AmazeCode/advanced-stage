package com.ac.springboot.feature.collector.service;

import cn.hutool.json.JSONUtil;
import com.ac.springboot.feature.collector.bean.Employee;
import com.ac.springboot.feature.collector.bean.Score;
import com.ac.springboot.feature.collector.util.MyCollector;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author: zhangyadong
 * @Date: 2022/8/9 23:45
 */
@Slf4j
@Service
public class CollectorService {

    List<Employee> init() {
        List<Employee> employees = new ArrayList<>();
        Employee employee1 = Employee.builder().name("大壮").subCompany("上海公司")
                .department("研发一部").age(28).salary(3000).build();
        Employee employee2 = Employee.builder().name("二牛").subCompany("上海公司")
                .department("研发一部").age(24).salary(2000).build();
        Employee employee3 = Employee.builder().name("铁柱").subCompany("上海公司")
                .department("研发二部").age(34).salary(5000).build();
        Employee employee4 = Employee.builder().name("翠花").subCompany("南京公司")
                .department("测试一部").age(27).salary(3000).build();
        Employee employee5 = Employee.builder().name("大壮").subCompany("南京公司")
                .department("测试二部").age(31).salary(4000).build();
        employees.add(employee1);
        employees.add(employee2);
        employees.add(employee3);
        employees.add(employee4);
        employees.add(employee5);
        return employees;
    }

    /**
     * @description: 根据公司查询员工
     * @param: company
     * @return: java.util.List<com.ac.springboot.feature.stream.bean.Employee>
     * @author: zhangyadong
     * @date: 2022/8/10 0:13
     */
    public List<Employee> filterEmployeeByCompany(String company) {
        // toList使用
        List<Employee> employees = init().stream()
                .filter(employee -> company.equals(employee.getSubCompany()))
                .collect(Collectors.toList());
        log.info("过滤返回List:{}",JSONUtil.toJsonStr(employees));

        // toMap使用
        Map<String, Employee> employee1Map = init().stream()
                .filter(employee -> company.equals(employee.getSubCompany()))
                .collect(Collectors.toMap(Employee::getName, Function.identity()));
        log.info("过滤返回Map(-):{}",JSONUtil.toJsonStr(employee1Map));

        /**
         *
         * { "GFG", "GeeksForGeeks" },
         * { "g", "geeks" },
         * { "GFG", "Geeks" }
         * 仅仅是有重复值时进行合并
         * Map:{g=geeks, GFG=GeeksForGeeks, geeksforgeeks}
         */
        Map<String, String> employee2Map = init().stream()
                .filter(employee -> company.equals(employee.getSubCompany()))
                .collect(Collectors.toMap(Employee::getName,Employee::getDepartment,(s,a) -> s + "," + a));
        log.info("过滤返回Map(二):{}",JSONUtil.toJsonStr(employee2Map));

        Stream<String[]>
                Ss1 = Stream
                .of(new String[][] { { "GFG", "GeeksForGeeks" },
                        { "g", "geeks" },
                        { "GFG", "Geeks" } });
        // Get Map from String
        LinkedHashMap<String, String>
                streamMap3 = Ss1
                .collect(Collectors.toMap(p -> p[0],
                                p -> p[1],
                                (s, a) -> s + ", " + a,
                                LinkedHashMap::new));
        log.info("过滤返回Map(三):{}",JSONUtil.toJsonStr(streamMap3));

        // toSet使用
        Set<Employee> set = init().stream()
                .filter(employee -> company.equals(employee.getSubCompany()))
                .collect(Collectors.toSet());
        log.info("过滤返回Set:{}",JSONUtil.toJsonStr(set));

        return employees;
    }

    /**
     * @description: 筛选出上海子公司的全部人员，并按照部门进行分组
     * @param:
     * @return: java.util.Map<java.lang.String,java.util.List<com.ac.springboot.feature.stream.bean.Employee>>
     * @author: zhangyadong
     * @date: 2022/8/10 0:14
     */
    public Map<String, List<Employee>> filterEmployeesThenGroupByStream() {
        // 过滤出上海公司员工，并且按照部分分组，返回map集合
        // 分组方式一
        // 先筛选
        List<Employee> employeeList = init().stream().filter(employee -> "上海公司".equals(employee.getSubCompany()))
                .collect(Collectors.toList());
        // 再分组
        Map<String, List<Employee>> employeeMap = new HashMap<>();
        for(Employee employee : employeeList){
            /**
             * computeIfAbsent 可用于判断map中key是否存在
             * 如果employee.getDepartment()存在，则返回employee.getDepartment()的value值，
             * 如果employee.getDepartment()不存在,执行new ArrayList()
             */
            List<Employee> groupList = employeeMap.computeIfAbsent(employee.getDepartment(), k -> new ArrayList<>());
            groupList.add(employee);
        }

        log.info("过滤分组返回Map:{}",JSONUtil.toJsonStr(employeeMap));

        // 分组方式二
        Map<String, List<Employee>> resultMap = init().stream().filter(employee -> "上海公司".equals(employee.getSubCompany()))
                .collect(Collectors.groupingBy(Employee::getDepartment));

        log.info("过滤分组返回Map:{}",JSONUtil.toJsonStr(resultMap));

        // 按照子公司维度将员工分组 groupingBy(x)默认使用toList()收集器
        Map<String, List<Employee>> departmentMap = init().stream().collect(Collectors.groupingBy(Employee::getDepartment));
        log.info("默认使用toList()收集器，按照子公司维度分组:{}",JSONUtil.toJsonStr(departmentMap));

        // 按照子公司分组，并统计每个子公司的员工数 groupingBy(x,y)使用y收集器
        Map<String, Long> departmentCountMap = init().stream()
                .collect(Collectors.groupingBy(Employee::getSubCompany,
                        Collectors.counting()));
        log.info("使用传入Collectors.counting()收集器，按照子公司维度统计员工数:{}",JSONUtil.toJsonStr(departmentCountMap));

        // partitioningBy 区别与groupingBy的地方分组收集器的分组函数返回值key始终为布尔值
        Map<Boolean, Long> partitionResultMap = init().stream()
                .collect(Collectors.partitioningBy(e -> "上海公司".equals(e.getSubCompany()),
                        Collectors.counting()));
        // true代表"上海公司"、false代表"南京公司"
        log.info("使用Collectors.partitioningBy，按照子公司维度统计员工数:{}",JSONUtil.toJsonStr(partitionResultMap));

        /**
         * 按照子公司+部门双层维度，统计各个部门内的人员数
         * 先按照子公司分组,再按照子公司下部门分组，统计部门下人数
         */
        Map<String, Map<String, Long>> subCompanyDepartmentMap = init().stream()
                .collect(Collectors.groupingBy(Employee::getSubCompany,
                        Collectors.groupingBy(Employee::getDepartment,
                                Collectors.counting())));
        log.info("按照子公司+部门双层维度，统计各个部门内的人员数:{}",JSONUtil.toJsonStr(subCompanyDepartmentMap));

        return resultMap;
    }

    /**
     * summingInt计算总数
     * @return
     */
    public Integer calculateSum() {
        Integer salarySum = init().stream().filter(employee -> "上海公司".equals(employee.getSubCompany()))
                .collect(Collectors.summingInt(Employee::getSalary));
        return salarySum;
    }

    /**
     * maxBy()、max()计算最高薪资员工
     * @return
     */
    public Employee highestSalaryEmployee() {
        // 计算最大薪水员工 方式一
        Optional<Employee> highestSalaryEmployee = init().stream()
                .filter(employee -> "上海公司".equals(employee.getSubCompany()))
                .collect(Collectors.maxBy(Comparator.comparingInt(Employee::getSalary)));

        Employee employee = highestSalaryEmployee.get();
        log.info("上海公司,最大薪水员工：{}",JSONUtil.toJsonStr(employee));

        // 计算最大薪水员工 方式二
        Optional<Employee> highestEmployee = init().stream()
                .filter(e -> "上海公司".equals(e.getSubCompany()))
                .max(Comparator.comparingInt(Employee::getSalary));

        employee = highestSalaryEmployee.get();
        log.info("上海公司,最大薪水员工：{}",JSONUtil.toJsonStr(employee));

        return employee;
    }

    /**
     * collectingAndThen 使用
     * 统计公司薪水最大员工
     * @return
     */
    public Employee collectingAndThen() {
        // collectingAndThen包裹另一个收集器，对其结果进行二次加工转换
        Employee emp = init().stream().filter(employee -> "上海公司".equals(employee.getSubCompany()))
                .collect(
                        Collectors.collectingAndThen(
                                Collectors.maxBy(Comparator.comparingInt(Employee::getSalary)),
                                Optional::get
                        )
                );
        log.info("上海公司,最大薪水员工：{}",JSONUtil.toJsonStr(emp));
        return emp;
    }

    /**
     * 自定义收集器
     * @return
     */
    public Integer myCollector() {
        Integer collect = Stream.of(new Score(1), new Score(2), new Score(3), new Score(4))
                .collect(new MyCollector<>(Score::getScore));
        log.info("自定义收集器：{}",collect);
        return collect;
    }
}
