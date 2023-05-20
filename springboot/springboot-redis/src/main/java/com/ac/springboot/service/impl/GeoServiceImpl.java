package com.ac.springboot.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.geo.*;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * geo实现坐标位置搜素
 *
 * @Author: zhangyadong
 * @Date: 2022/10/23 18:49
 */
@Slf4j
@Service
public class GeoServiceImpl {

    private final String KEY = "geo:drinks";

    /**
     * The Redis template.
     */
    @Resource
    StringRedisTemplate redisGeoTemplate;

    public void add(String name, Point point) {
        Long add = redisGeoTemplate.opsForGeo().add(KEY, point, name);
        log.info("成功添加名称：{} 的坐标信息信息：{}", name, point);
    }


    public void get(String... names) {
        List<org.springframework.data.geo.Point> position = redisGeoTemplate.opsForGeo().position(KEY, names);
        log.info("获取名称为：{} 的坐标信息：{}", names, position);
    }

    public void del(String... names) {
        Long remove = redisGeoTemplate.opsForGeo().remove(KEY, names);
        log.info("删除名称为：{} 的坐标信息数量：{}", names, remove);
    }

    /**
     * 根据坐标 获取指定范围的位置
     *
     * @param point
     * @param distance
     * @return
     */
    public GeoResults getNearByXY(Point point, Distance distance) {
        Circle circle = new Circle(point, distance);
        RedisGeoCommands.GeoRadiusCommandArgs args = RedisGeoCommands.GeoRadiusCommandArgs.
                newGeoRadiusArgs().
                includeDistance(). // 包含距离
                includeCoordinates(). // 包含坐标
                sortAscending(). // 排序 还可选sortDescending()
                limit(5); // 获取前多少个
        GeoResults geoResults = redisGeoTemplate.opsForGeo().radius(KEY, circle, args);
        log.info("根据坐标获取：{} {} 范围的数据：{}", point, distance, geoResults);
        return geoResults;
    }

    /**
     * 根据一个位置，获取指定范围内的其他位置
     *
     * @param name
     * @param distance
     * @return
     */
    public GeoResults getNearByPlace(String name, Distance distance) {
        RedisGeoCommands.GeoRadiusCommandArgs args = RedisGeoCommands.GeoRadiusCommandArgs.
                newGeoRadiusArgs().
                includeDistance(). // 包含距离
                includeCoordinates(). // 包含坐标
                sortAscending(). // 排序 还可选sortDescending()
                limit(5); // 获取前多少个
        GeoResults geoResults = redisGeoTemplate.opsForGeo()
                .radius(KEY, name, distance, args);
        log.info("根据位置：{} 获取： {} 范围的数据：{}", name, distance, geoResults);
        return geoResults;
    }

    /**
     * 获取GEO HASH
     *
     * @param names
     * @return
     */
    public List<String> getGeoHash(String... names) {
        List<String> hash = redisGeoTemplate.opsForGeo().hash(KEY, names);
        log.info("names：{} 对应的hash：{}", names, hash);
        return hash;
    }
}
