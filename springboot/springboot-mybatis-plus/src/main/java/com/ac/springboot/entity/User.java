package com.ac.springboot.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Description: 用户实体
 * @author: zhangyadong
 * @Date: 2019/11/18 0018 下午 4:33
 * @version: V1.0
 */
@Data//会自动添加getter、setter、hashCode、equals、toString方法
public class User {

    /*
        主键生成策略：
        AUTO(0): 自动增长
        NONE(1)： 没有策略,需要手动设置
        INPUT(2): 需要程序员自己设置
        ASSIGN_ID(3): 生成19位的随机值  雪花算法,分布式id
        ASSIGN_UUID(4): 生成一个uuid值
     */
    @TableId(type = IdType.ASSIGN_ID) // 不写时id默认生成策略ASSIGN_ID,数据库id不是自动递增时会生成一个id
    private Long id;
    private String name;
    private Integer age;
    private String email;

    // 创建时间
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    // 更新时间
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    /**
     * mp实现乐观锁
     */
    @Version
    @TableField(fill = FieldFill.INSERT)
    private Integer version;
}
