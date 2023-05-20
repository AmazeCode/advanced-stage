package com.ac.springboot.service;

import com.ac.springboot.dto.RankDTO;

import java.util.List;

/**
 * @Author: zhangyadong
 * @Date: 2022/7/27 23:59
 */
public interface ScoreService {

    /**
     * 获取前n名的排行榜数据
     *
     * @param num
     * @return
     */
    List<RankDTO> findTopByNum(int num);

    /**
     * 插入用户数据
     *
     * @param userId
     * @param score
     * @return
     */
    RankDTO updateRank(long userId, float score);

    /**
     * 获取用户的排行榜位置
     *
     * @param userId
     * @return
     */
    RankDTO getRankDTO(long userId);

    /**
     * 获取用户所在排行榜的位置，以及排行榜中其前后n个用户的排行信息
     *
     * @param userId
     * @param n
     * @return
     */
    List<RankDTO> getRankAroundUser(Long userId, int n);
}
