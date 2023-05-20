package com.ac.springboot;

import com.ac.springboot.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 *  @Description: Redis测试类
 *  @author: zhangyadong
 *  @Date: 2019/11/23 13:57
 *  @version: V1.0
 */
@SpringBootTest
public class RedisTest {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void test() throws Exception {
        //redis设置值
        stringRedisTemplate.opsForValue().set("aaa", "111");
    }

    @Test
    public void testObj() throws Exception {
        //初始化用户
        User user=new User("aa@126.com", "aa", "aa123456", "aa","123");

        ValueOperations<String, User> operations = redisTemplate.opsForValue();
        operations.set("com.ac.springboot", user);
        operations.set("com.ac.springboot-cs", user,1, TimeUnit.SECONDS);
        Thread.sleep(1000);
        //redisTemplate.delete("com.neo.f");
        boolean exists=redisTemplate.hasKey("com.ac.springboot");
        if(exists){
            System.out.println("exists is true");
        }else{
            System.out.println("exists is false");
        }
        // Assert.assertEquals("aa", operations.get("com.neo.f").getUserName());
    }
}
