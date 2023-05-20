package com.ac.springboot;

import com.ac.springboot.mapper.UserMapper;
import com.ac.springboot.entity.User;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

/**
 * @Description: 测试类
 * @author: zhangyadong
 * @Date: 2019/11/18 0018 下午 4:54
 * @version: V1.0
 */
@SpringBootTest
public class SpringBootMybatisPlusTest {

    @Autowired
    private UserMapper userMapper;

    /**
     * @Description 测试查询
     * @params []
     * @return void
     * @author zhangyadong
     * @date 2019/11/18 0018 下午 5:15
     */
    @Test
    public void testSelectOne() {
        User user = userMapper.selectById(1196356027696672769L);
        System.out.println(user);
    }

    /**
     * @Description 测试新增
     * @params []
     * @return void
     * @author zhangyadong
     * @date 2019/11/18 0018 下午 5:15
     */
    @Test
    public void testInsert() {
        User user = new User();
        user.setName("小马");
        user.setAge(26);
        user.setEmail("xiaoma@tooool.org");
        assertThat(userMapper.insert(user)).isGreaterThan(0);
        assertThat(user.getId()).isNotNull();
    }

    /**
     * @Description 删除测试
     * @params []
     * @return void
     * @author zhangyadong
     * @date 2019/11/18 0018 下午 5:31
     */
    @Test
    public void testDelete() {
        assertThat(userMapper.deleteById(1196358108046213121L)).isGreaterThan(0);
    }

    /**
     * Test update.
     *
     * @return void
     * @Description 更新测试
     * @params []
     * @author zhangyadong
     * @date 2019 /11/18 0018 下午 5:31
     */
    @Test
    public void testUpdate() {
        User user = userMapper.selectById(7);
        user.setAge(16);
        //userMapper.updateById(user);
        LambdaUpdateWrapper<User> userLambdaUpdateWrapper = Wrappers.lambdaUpdate();
        userLambdaUpdateWrapper.eq(User::getName, "小马");
        userLambdaUpdateWrapper.set(User::getEmail,"456@qq.com");
        userMapper.update(user,userLambdaUpdateWrapper);
    }

    /**
     * @Description 查询全部数据测试
     * @params []
     * @return void
     * @author zhangyadong
     * @date 2019/11/18 0018 下午 5:30
     */
    @Test
    public void findAll() {
        List<User> userList = userMapper.selectList(null);
        userList.forEach(System.out::println);
    }

    /**
     * @Description 条件查询
     * @params []
     * @return void
     * @author zhangyadong
     * @date 2019/11/18 0018 下午 5:30
     */
    @Test
    public void testSelectCondition() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.select("max(id) as id");
        List<User> userList = userMapper.selectList(wrapper);
        userList.forEach(System.out::println);
    }

    /**
     * @Description 测试分页
     * @params []
     * @return void
     * @author zhangyadong
     * @date 2019/11/18 0018 下午 5:51
     */
    @Test
    public void testPage() {
        System.out.println("----- baseMapper 自带分页 ------");
        Page<User> page = new Page<>(1, 2);
        IPage<User> userIPage = userMapper.selectPage(page, new QueryWrapper<User>()
                .gt("age", 26));
        assertThat(page).isSameAs(userIPage);
        System.out.println("总条数 ------> " + userIPage.getTotal());
        System.out.println("当前页数 ------> " + userIPage.getCurrent());
        System.out.println("当前每页显示数 ------> " + userIPage.getSize());
        print(userIPage.getRecords());
        System.out.println("----- baseMapper 自带分页 ------");
    }

    private <T> void print(List<T> list) {
        if (!CollectionUtils.isEmpty(list)) {
            list.forEach(System.out::println);
        }
    }
}
