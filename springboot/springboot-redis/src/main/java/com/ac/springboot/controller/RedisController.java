package com.ac.springboot.controller;

import com.ac.springboot.dto.RankDTO;
import com.ac.springboot.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * redis 测试控制层
 *
 * @Author: zhangyadong
 * @Date: 2022/7/27 23:52
 */
@RestController
@RequestMapping("/zset")
public class RedisController {

    @Autowired
    private ScoreService scoreService;

    /**
     * 获取前n名的排行榜数据
     *
     * @param n
     * @return
     */
    @GetMapping(path = "/topn")
    public List<RankDTO> showTopN(int n) {
        return scoreService.findTopByNum(n);
    }

    /**
     * 插入新的数据排名
     *
     * @param userId
     * @param score
     * @return
     */
    @GetMapping(path = "/update")
    public RankDTO updateScore(long userId, float score) {
        return scoreService.updateRank(userId, score);
    }

    /**
     * 获取用户的排行榜位置
     *
     * @param userId
     * @return
     */
    @GetMapping(path = "/rank")
    public RankDTO queryRank(long userId) {
        return scoreService.getRankDTO(userId);
    }

    /**
     * 获取用户所在排行榜的位置，以及排行榜中其前后n个用户的排行信息
     *
     * @param userId
     * @param n
     * @return
     */
    @GetMapping(path = "/around")
    public List<RankDTO> around(long userId, int n) {
        return scoreService.getRankAroundUser(userId, n);
    }
}
