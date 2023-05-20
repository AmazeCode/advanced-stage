package com.ac.springboot.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * MP 自动填充
 *
 * @Author: zhangyadong
 * @Date: 2022/10/17
 * @Version: v1.0
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    /*
        @TableField(fill = FieldFill.INSERT_UPDATE),修改时自动填充时,仅仅通过id修改
        才会自动修改,非id字段不会修改
        userMapper.updateById(user); 可以自动填充
        userMapper.update(user,userLambdaUpdateWrapper); user有id时可以自动填充,没id时不能自动填充
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        // 实体属性上有@TableField(fill = FieldFill.INSERT) 时，执行该方法
        // 新增/更新时自动更新时间
        setFieldValByName("createTime", LocalDateTime.now(), metaObject);
        setFieldValByName("updateTime", LocalDateTime.now(), metaObject);
        // 设置版本
        setFieldValByName("version", 1, metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        // 实体属性上有@TableField(fill = FieldFill.INSERT_UPDATE)时，执行该方法
        // 更新时，自动更新时间
        setFieldValByName("updateTime", LocalDateTime.now(), metaObject);
    }
}
