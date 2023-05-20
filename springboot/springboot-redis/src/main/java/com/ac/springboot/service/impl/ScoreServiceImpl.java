package com.ac.springboot.service.impl;

import com.ac.springboot.dto.RankDTO;
import com.ac.springboot.service.ScoreService;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * @Author: zhangyadong
 * @Date: 2022/7/28 0:00
 */
@Service
public class ScoreServiceImpl implements ScoreService {

    @Resource
    StringRedisTemplate stringRedisTemplate;

    @Override
    public List<RankDTO> findTopByNum(int num) {
        //查询集合中指定顺序的值和score，0, -1 表示获取全部的集合内容
        Set<ZSetOperations.TypedTuple<String>> tupleSet = stringRedisTemplate.opsForZSet().rangeWithScores("global_rank", 0, num - 1);
        List<RankDTO> rankList = new ArrayList<>(tupleSet.size());
        long rank = 1;
        for (ZSetOperations.TypedTuple<String> sub : tupleSet) {
            rankList.add(new RankDTO(rank++, Math.abs(sub.getScore().floatValue()), Long.parseLong(sub.getValue())));
        }
        return rankList;
    }

    @Override
    public RankDTO updateRank(long userId, float score) {
        //添加一个元素, zset与set最大的区别就是每个元素都有一个score，因此有个排序的辅助功能;  zadd
        stringRedisTemplate.opsForZSet().add("global_rank", String.valueOf(userId), -score);
        Long rank = stringRedisTemplate.opsForZSet().rank("global_rank", String.valueOf(userId));
        return new RankDTO(rank + 1, score, userId);
    }

    @Override
    public RankDTO getRankDTO(long userId) {
        // 获取排行， 因为默认是0为开头，因此实际的排名需要+1
        Long rank = stringRedisTemplate.opsForZSet().rank("global_rank", String.valueOf(userId));
        if (rank == null) {
            // 没有排行时，直接返回一个默认的
            return new RankDTO(-1L, 0F, userId);
        }

        // 获取积分
        Double score = stringRedisTemplate.opsForZSet().score("global_rank", String.valueOf(userId));
        return new RankDTO(rank + 1, Math.abs(score.floatValue()), userId);
    }

    @Override
    public List<RankDTO> getRankAroundUser(Long userId, int n) {
        // 首先是获取用户对应的排名
        RankDTO RankDTO = getRankDTO(userId);
        if (RankDTO.getRank() <= 0) {
            // 用户没有上榜时，不返回
            return Collections.emptyList();
        }

        // 因为实际的排名是从0开始的，所以查询周边排名时，需要将n-1
        // 查询集合中指定顺序的值和score，0, -1 表示获取全部的集合内容
        Set<ZSetOperations.TypedTuple<String>> result =
                stringRedisTemplate.opsForZSet().rangeWithScores("global_rank", Math.max(0, RankDTO.getRank() - n - 1), RankDTO.getRank() + n - 1);
        List<RankDTO> rankList = new ArrayList<>(result.size());
        long rank = RankDTO.getRank() - n;
        for (ZSetOperations.TypedTuple<String> sub : result) {
            rankList.add(new RankDTO(rank++, Math.abs(sub.getScore().floatValue()), Long.parseLong(sub.getValue())));
        }
        return rankList;
    }

    /**
     * 添加一个元素, zset与set最大的区别就是每个元素都有一个score，因此有个排序的辅助功能;  zadd
     * <p>
     * 新增元素时，用起来和set差不多，无非是多一个score的参数指定而已
     * 如果元素存在，会用新的score来替换原来的，返回0；如果元素不存在，则会会新增一个
     *
     * @param key
     * @param value
     * @param score
     */
    public void add(String key, String value, double score) {
        stringRedisTemplate.opsForZSet().add(key, value, score);
    }

    /**
     * 删除元素 zrem
     * <p>
     * 删除就和普通的set没啥区别了
     *
     * @param key
     * @param value
     */
    public void remove(String key, String value) {
        stringRedisTemplate.opsForZSet().remove(key, value);
    }

    /**
     * score的增加or减少 zincrby
     * <p>
     * zset中的元素塞入之后，可以修改其score的值，通过 zincrby 来对score进行加/减；当元素不存在时，则会新插入一个
     * 从上面的描述来看，zincrby 与 zadd 最大的区别是前者是增量修改；后者是覆盖score方式
     *
     * @param key
     * @param value
     * @param score
     */
    public Double incrScore(String key, String value, double score) {
        return stringRedisTemplate.opsForZSet().incrementScore(key, value, score);
    }

    /**
     * 查询value对应的score   zscore
     * 这个需要注意的是，当value在集合中时，返回其score；如果不在，则返回null
     *
     * @param key
     * @param value
     * @return
     */
    public Double score(String key, String value) {
        return stringRedisTemplate.opsForZSet().score(key, value);
    }

    /**
     * 判断value在zset中的排名  zrank
     * 前面是获取value对应的score；这里则是获取排名；这里score越小排名越高;
     * 从这个使用也可以看出结合4、5, 用zset来做排行榜可以很简单的获取某个用户在所有人中的排名与积分
     *
     * @param key
     * @param value
     * @return
     */
    public Long rank(String key, String value) {
        return stringRedisTemplate.opsForZSet().rank(key, value);
    }

    /**
     * 返回集合的长度
     *
     * @param key
     * @return
     */
    public Long size(String key) {
        return stringRedisTemplate.opsForZSet().zCard(key);
    }


    /**
     * 查询集合中指定顺序的值， 0 -1 表示获取全部的集合内容  zrange
     * <p>
     * 返回有序的集合，score小的在前面
     *
     * @param key
     * @param start
     * @param end
     * @return
     */
    public Set<String> range(String key, int start, int end) {
        return stringRedisTemplate.opsForZSet().range(key, start, end);
    }

    // 因为是有序，所以就可以获取指定范围的数据，下面有两种方式
    //根据排序位置获取数据
    //根据score区间获取排序位置

    /**
     * 查询集合中指定顺序的值和score，0, -1 表示获取全部的集合内容
     *
     * @param key
     * @param start
     * @param end
     * @return
     */
    public Set<ZSetOperations.TypedTuple<String>> rangeWithScore(String key, int start, int end) {
        return stringRedisTemplate.opsForZSet().rangeWithScores(key, start, end);
    }

    /**
     * 查询集合中指定顺序的值  zrevrange
     * <p>
     * 返回有序的集合中，score大的在前面
     *
     * @param key
     * @param start
     * @param end
     * @return
     */
    public Set<String> revRange(String key, int start, int end) {
        return stringRedisTemplate.opsForZSet().reverseRange(key, start, end);
    }

    /**
     * 根据score的值，来获取满足条件的集合  zrangebyscore
     *
     * @param key
     * @param min
     * @param max
     * @return
     */
    public Set<String> sortRange(String key, int min, int max) {
        return stringRedisTemplate.opsForZSet().rangeByScore(key, min, max);
    }
}
