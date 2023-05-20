package com.ac.springboot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author: zhangyadong
 * @Date: 2022/7/27 23:54
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RankDTO implements Serializable {

    private static final long serialVersionUID = 6674951641221539360L;

    /**
     * 排名
     */
    private Long rank;

    /**
     * 积分
     */
    private Float score;

    /**
     * 用户id
     */
    private Long userId;
}
