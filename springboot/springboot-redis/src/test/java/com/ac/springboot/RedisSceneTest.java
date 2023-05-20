package com.ac.springboot;

import cn.hutool.json.JSONUtil;
import com.ac.springboot.service.impl.GeoServiceImpl;
import com.ac.springboot.service.impl.IncrServiceImpl;
import com.ac.springboot.service.impl.SignByMonthServiceImpl;
import com.ac.springboot.service.impl.SignByRangeServiceImpl;
import com.ac.springboot.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.geo.*;
import org.springframework.data.redis.core.DefaultTypedTuple;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Redis 使用场景
 *
 * @Author: zhangyadong
 * @Date: 2022/10/20
 * @Version: v1.0
 */
@Slf4j
@SpringBootTest
public class RedisSceneTest {

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    private RedisUtil redisUtil;

    /************************ Set实现抽奖  **********************/
    /*
        功能实现需要的API
            SADD key member1 [member2]：添加一个或者多个参与用户；
            SRANDMEMBER KEY [count]：随机返回一个或者多个用户；
            SPOP key：随机返回一个或者多个用户，并删除返回的用户；

        SRANDMEMBER 和 SPOP  主要用于两种不同的抽奖模式，SRANDMEMBER 适用于一个用户可中奖多次的场景（就是中奖之后，不从用户池中移除，继续参与其他奖项的抽取）；
        SPOP 就适用于仅能中一次的场景（一旦中奖，就将用户从用户池中移除，后续的抽奖，就不可能再抽到该用户）；
        通常 SPOP 会用的会比较多。
     */
    // 抽奖key
    private final String KEY_LOTTERY_PROFIX = "redis:lottery";

    @Test
    void test() {
        Integer raffleId = 1;
        //join(raffleId, 1000, 1001, 2233, 7890, 44556, 74512);
        List lucky = lucky(raffleId, 2);
        log.info("活动：{} 的幸运中奖用户是：{}", raffleId, lucky);
    }

    public void join(Integer raffleId, Integer... userIds) {
        String key = KEY_LOTTERY_PROFIX + raffleId;
        redisTemplate.opsForSet().add(key, userIds);
    }

    public List lucky(Integer raffleId, long num) {
        String key = KEY_LOTTERY_PROFIX + raffleId;
        // 随机抽取 抽完之后将用户移除奖池
        //List list = redisTemplate.opsForSet().pop(key, num);
        // 随机抽取 抽完之后用户保留在池子里
        List list = redisTemplate.opsForSet().randomMembers(key, num);
        return list;
    }

    /*
      认识的人/好友推荐
      功能所需的命令:
        SADD key member [member …]：集合中添加元素，缓存好友列表
        SDIFF key [key …]：取两个集合间的差集，找出可以推荐的用户
     */
    private String KEY_FL_PROFIX = "FL:";

    @Test
    void mayRecognizeTest () {
        String user1Key = KEY_FL_PROFIX + "user1";
        String[] user1s = {"user1","user2","user3"};
        redisTemplate.opsForSet().add(user1Key, user1s);

        String user2Key = KEY_FL_PROFIX + "user2";
        String[] user2s = {"user1","user4","user5"};
        redisTemplate.opsForSet().add(user2Key, user2s);

        // 得到user1除去user2相同元素后剩余的元素 (user1可能认识的人)
        Set user2Possible = redisTemplate.opsForSet().difference(user1Key,user2Key);
        user2Possible.stream().forEach(dif -> System.out.println(dif));

        // 得到user2除去user1相同元素后剩余的元素 (user2可能认识的人)
        Set user1Possible = redisTemplate.opsForSet().difference(user1Key,user2Key);
        user1Possible.stream().forEach(dif -> System.out.println(dif));
    }

    /************************ Set实现点赞/收藏功能  **********************/
    /*
        有互动属性APP一般都会有点赞/收藏/喜欢等功能，来提升用户之间的互动。
        传统的实现：
            用户点赞之后，在数据库中记录一条数据，同时一般都会在主题库中记录一个点赞/收藏汇总数，来方便显示；
        Redis方案：
            基于Redis的集合（Set），记录每个帖子/文章对应的收藏、点赞的用户数据，同时set还提供了检查集合中是否存在指定用户，用户快速判断用户是否已经点赞过

        功能实现需要的API
            SADD key member1 [member2]：添加一个或者多个成员（点赞）
            SCARD key：获取所有成员的数量（点赞数量）
            SISMEMBER key member：判断成员是否存在（是否点赞）
            SREM key member1 [member2] ：移除一个或者多个成员（点赞数量）
     */
    // 点赞/收藏 key
    private final String KEY_LIKE_ARTICLE_PROFIX = "redis:like:article";

    @Test
    void likeArticleTest() {
        // 文章id
        long articleId = 100;
        Long likeNum = like(articleId, 1001, 1002, 2001, 3005, 4003);
        unLike(articleId, 2001);
        likeNum = likeNum(articleId);
        boolean b2001 = isLike(articleId, 2001);
        boolean b3005 = isLike(articleId, 3005);
        log.info("文章：{} 点赞数量：{} 用户2001的点赞状态：{} 用户3005的点赞状态：{}", articleId, likeNum, b2001, b3005);
    }

    /**
     * 收藏/点赞
     *
     * @param articleId 文章ID
     * @return 点赞数量
     */
    public Long like(Long articleId, Integer... userIds) {
        String key = KEY_LIKE_ARTICLE_PROFIX + articleId;
        Long add = redisTemplate.opsForSet().add(key, userIds);
        return add;
    }

    /**
     * 取消收藏/点赞
     *
     * @param articleId 文章id
     * @param userIds   用户id
     * @return
     */
    public Long unLike(Long articleId, Integer... userIds) {
        String key = KEY_LIKE_ARTICLE_PROFIX + articleId;
        Long remove = redisTemplate.opsForSet().remove(key, userIds);
        return remove;
    }

    /**
     * 收藏/点赞数量
     *
     * @param articleId
     * @return
     */
    public Long likeNum(Long articleId) {
        String key = KEY_LIKE_ARTICLE_PROFIX + articleId;
        Long size = redisTemplate.opsForSet().size(key);
        return size;
    }

    /**
     * 是否收藏/点赞
     *
     * @param articleId 文章id
     * @param userId    用户id
     * @return
     */
    public Boolean isLike(Long articleId, Integer userId) {
        String key = KEY_LIKE_ARTICLE_PROFIX + articleId;
        return redisTemplate.opsForSet().isMember(key, userId);
    }

    /************************ ZSet实现排行榜  **********************/
    /*
        排名、排行榜、热搜榜是很多APP、游戏都有的功能，常用于用户活动推广、竞技排名、热门信息展示等功能；
        比如上面的热搜榜，热度数据来源于全网用户的贡献，但用户只关心热度最高的前50条。
        常规的做法：就是将用户的名次、分数等用于排名的数据更新到数据库，然后查询的时候通过Order by + limit 取出前50名显示，如果是参与用户不多，
            更新不频繁的数据，采用数据库的方式也没有啥问题，但是一旦出现爆炸性热点资讯（比如：大陆收复湾湾，xxx某些绿了等等），
            短时间会出现爆炸式的流量，瞬间的压力可能让数据库扛不住；
        Redis方案：将热点资讯全页缓存，采用Redis的有序队列（Sorted Set）来缓存热度（SCORES），即可瞬间缓解数据库的压力，
            同时轻松筛选出热度最高的50条；
        功能实现需要的命令：
            ZADD key score1 member1 [score2 member2]：添加并设置SCORES，支持一次性添加多个；
            ZREVRANGE key start stop [WITHSCORES] ：根据SCORES降序排列；
            ZRANGE key start stop [WITHSCORES] ：根据SCORES降序排列；
     */
    private final String KEY_RANKING_PROFIX = "redis:ranking";

    @Test
    void rankingTest() {
        zsetAdd(1001, (double) 60);
        zsetAdd(1002, (double) 80);
        zsetAdd(1003, (double) 100);
        zsetAdd(1004, (double) 90);
        zsetAdd(1005, (double) 70);
        // 取所有
        Set<DefaultTypedTuple> range = zsetRange(0, -1);
        log.info("所有用户排序：{}", range);
        // 前三名
        range = zsetRange(0, 2);
        log.info("前三名排序：{}", range);
    }

    /**
     * 添加排行数据
     *
     * @param userId 用户id
     * @param score  分数(用于排名)
     * @return
     */
    public Boolean zsetAdd(Integer userId, Double score) {
        Boolean add = redisTemplate.opsForZSet().add(KEY_RANKING_PROFIX, userId, score);
        return add;
    }

    /**
     * 排序
     *
     * @param min 最小
     * @param max 最大  如果min=0,max=-1代表获取全部;如果min=0,max=2代表获取前三条数据
     * @return
     */
    public Set<DefaultTypedTuple> zsetRange(long min, long max) {
        // 降序
        Set<DefaultTypedTuple> set = redisTemplate.opsForZSet().reverseRangeWithScores(KEY_RANKING_PROFIX, min, max);
        // 升序
        //Set<DefaultTypedTuple> set = redisTemplate.opsForZSet().rangeWithScores(KEY_RANKING, min, max);
        return set;
    }

    /************************ PV统计（incr自增计数）  **********************/
    /*
        Page View（PV）指的是页面浏览量，是用来衡量流量的一个重要标准，也是数据分析很重要的一个依据；
            通常统计规则是页面被展示一次，就加一
            也可用做创建分布式自增id
        功能所需命令：
            INCR：将 key 中储存的数字值增一
     */
    private final String KEY_INCR_PROFIX = "redis:incr";

    @Test
    void pvTest () {
        RedisAtomicLong redisAtomicLong = null;
        if(!redisTemplate.hasKey(KEY_INCR_PROFIX)){
            redisTemplate.opsForValue().increment(KEY_INCR_PROFIX, 1);
            log.info("使用redis缓存保存数据成功");
            redisAtomicLong = new RedisAtomicLong(KEY_INCR_PROFIX,redisTemplate);
            //incr 默认初始值从0开始，
            //可以设置初始值，
            redisAtomicLong.set(0);
        }
        redisAtomicLong = new RedisAtomicLong(KEY_INCR_PROFIX,redisTemplate);
        // 递增1
        long andIncrement1 = redisAtomicLong.incrementAndGet();
        log.info("pv浏览数:{}",andIncrement1);
        long andIncrement2 = redisAtomicLong.incrementAndGet();
        log.info("pv浏览数:{}",andIncrement2);
        // pv浏览数减1
        long andDecrement = redisAtomicLong.decrementAndGet();
        log.info("pv浏览数减少后:{}",andDecrement);
    }
    /************************ UV统计（HeyperLogLog）  **********************/
    /*
        前面，介绍了通过（INCR）方式来实现页面的PV；除了PV之外，UV（独立访客）也是一个很重要的统计数据；
        但是如果要想通过计数（INCR）的方式来实现UV计数，就非常的麻烦，增加之前，需要判断这个用户是否访问过；那判断依据就需要额外的方式再进行记录。
        你可能会说，不是还有Set嘛！一个页面弄个集合，来一个用户塞（SADD）一个用户进去，要统计UV的时候，再通过SCARD汇总一下数量，就能轻松搞定了；
        此方案确实能实现UV的统计效果，但是忽略了成本；如果是普通页面，几百、几千的访问，可能造成的影响微乎其微，
        如果一旦遇到爆款页面，动辄上千万、上亿用户访问时，就一个页面UV将会带来非常大的内存开销，对于如此珍贵的内存来说，这显然是不划算的。
        此时，HeyperLogLog数据结构，就能完美的解决这一问题，它提供了一种不精准的去重计数方案，注意！这里强调一下，是不精准的，会存在误差，
        不过误差也不会很大，**标准的误差率是0.81%**，这个误差率对于统计UV计数，是能够容忍的；所以，不要将这个数据结构拿去做精准的去重计数。
        另外，HeyperLogLog 是会占用12KB的存储空间，虽然说，Redis 对 HeyperLogLog 进行了优化，在存储数据比较少的时候，采用了稀疏矩阵存储，
        只有在数据量变大，稀疏矩阵空间占用超过阈值时，才会转为空间为12KB的稠密矩阵；相比于成千、上亿的数据量，这小小的12KB，简直是太划算了；
        但是还是建议，不要将其用于数据量少，且频繁创建 HeyperLogLog 的场景，避免使用不当，造成资源消耗没减反增的不良效果。

        功能所需命令：
            PFADD key element [element ...]：增加计数（统计UV）
            PFCOUNT key [key ...]：获取计数（货物UV）
            PFMERGE destkey sourcekey [sourcekey ...]：将多个 HyperLogLog 合并为一个 HyperLogLog（多个合起来统计）

            # 添加三个用户的访问
            127.0.0.1:6379> PFADD uv:page:1 user1 user2 user3
            (integer) 1
            # 获取UV数量
            127.0.0.1:6379> PFCOUNT uv:page:1
            (integer) 3
            # 再添加三个用户的访问  user3是重复用户
            127.0.0.1:6379> PFADD uv:page:1 user3 user4 user5
            (integer) 1
            # 获取UV数量 user3是重复用户 所以这里返回的是5
            127.0.0.1:6379> PFCOUNT uv:page:1
            (integer) 5
     */
    private final String KEY_UV_PAGE_PROFIX = "redis:uv:page:";

    @Test
    void uvTest () {
        Integer pageId = 2;
        for (int i = 0; i < 10000; i++) {
            uv(pageId, i);
        }
        for (int i = 0; i < 10000; i++) {
            uv(pageId, i);
        }

        Long uv = getUv(pageId);
        // 输出： pageId:2 uv:9987
        log.info("pageId:{} uv:{}", pageId, uv);
    }
    /**
     * 用户访问页面
     * @param pageId
     * @param userId
     * @return
     */
    private Long uv(Integer pageId, Integer userId) {
        String key = KEY_UV_PAGE_PROFIX + pageId;
        // 添加访问用户 数据格式Hex
        return redisTemplate.opsForHyperLogLog().add(key, userId);
    }

    /**
     * 统计页面的UV
     * @param pageId
     * @return
     */
    private Long getUv(Integer pageId) {
        String key = KEY_UV_PAGE_PROFIX + pageId;
        // 统计
        return redisTemplate.opsForHyperLogLog().size(key);
    }

    /************************ 去重（BloomFiler）  **********************/
    /*
        通过上面HeyperLogLog的学习，我们掌握了一种不精准的去重计数方案，但是有没有发现，他没办法获取某个用户是否访问过；
        理想中，我们是希望有一个PFEXISTS的命令，来判断某个key是否存在，然而HeyperLogLog并没有；要想实现这一需求，就得 BloomFiler 上场了。
        什么是Bloom Filter？
            Bloom Filter是由Bloom在1970年提出的一种多哈希函数映射的快速查找算法。
            通常应用在一些需要快速判断某个元素是否属于集合，但是并不严格要求100%正确的场合。
            基于一种概率数据结构来实现，是一个有趣且强大的算法。

         举个例子：假如你写了一个爬虫，用于爬取网络中的所有页面，当你拿到一个新的页面时，如何判断这个页面是否爬取过？
         普通做法：每爬取一个页面，往数据库插入一行数据，记录一下URL，每次拿到一个新的页面，就去数据库里面查询一下，存在就说明爬取过；
         普通做法的缺点：少量数据，用传统方案没啥问题，如果是海量数据，每次爬取前的检索，将会越来越慢；如果你的爬虫只关心内容，
            对来源数据不太关心的话，这部分数据的存储，也将消耗你很大的物理资源；
            此时通过 BloomFiler 就能以很小的内存空间作为代价，即可轻松判断某个值是否存在。
            同样，BloomFiler 也不那么精准，在默认参数情况下，是存在1%左右的误差；

            但是 BloomFiler是允许通过error_rate（误差率）以及initial_size（预计大小）来设置他的误差比例
            error_rate：误差率，越低，需要的空间就越大；
            initial_size：预计放入值的数量，当实际放入的数量大于设置的值时，误差率就会逐渐升高；所以为了避免误差率，
            可以提前做好估值，避免再次大的误差；

        想使用Docker安装的redis添加bloomfilter插件，以实现一个布隆过滤器，经过搜索发现docker中有整合的redis与bloomfilter插件的镜像。
        直接使用redislabs/rebloom镜像
        使用docker拉取镜像安装：docker pull redislabs/rebloom

        docker run -p6379:6379 redislabs/rebloom
        启动容器: docker run -d -p 6379:6379 --name redis-redisbloom redislabs/rebloom
        docker exec -it redis-redisbloom bash
            功能所需的命令
                bf.add 添加单个元素
                bf.madd 批量添加
                bf.exists 检测元素是否存在
                bf.mexists 批量检测
     */
    // 设置错误率
    private static RedisScript<Boolean> bfreserveScript = new DefaultRedisScript<>("return redis.call('bf.reserve', KEYS[1], ARGV[1], ARGV[2])", Boolean.class);
    // bf 添加脚本
    private static RedisScript<Boolean> bfaddScript = new DefaultRedisScript<>("return redis.call('bf.add', KEYS[1], ARGV[1])", Boolean.class);
    // bf 判断存在脚本
    private static RedisScript<Boolean> bfexistsScript = new DefaultRedisScript<>("return redis.call('bf.exists', KEYS[1], ARGV[1])", Boolean.class);
    // bf 批量添加
    private static String bfmaddScript = "return redis.call('bf.madd', KEYS[1], %s)";
    // bf 批量检测
    private static String bfmexistsScript = "return redis.call('bf.mexists', KEYS[1], %s)";

    /**
     * 设置错误率和大小（需要在添加元素前调用，若已存在元素，则会报错）
     * 错误率越低，需要的空间越大
     *
     * @param key
     * @param errorRate   错误率，默认0.01
     * @param initialSize 默认100，预计放入的元素数量，当实际数量超出这个数值时，误判率会上升，尽量估计一个准确数值再加上一定的冗余空间
     * @return
     */
    public Boolean bfreserve(String key, double errorRate, int initialSize) {
        return (boolean)redisTemplate.execute(bfreserveScript, Arrays.asList(key), String.valueOf(errorRate), String.valueOf(initialSize));
    }

    /**
     * 添加元素
     *
     * @param key
     * @param value
     * @return true表示添加成功，false表示添加失败（存在时会返回false）
     */
    public Boolean bfadd(String key, String value) {
        return (boolean)redisTemplate.execute(bfaddScript, Arrays.asList(key), value);
    }

    /**
     * 查看元素是否存在（判断为存在时有可能是误判，不存在是一定不存在）
     *
     * @param key
     * @param value
     * @return true表示存在，false表示不存在
     */
    public Boolean bfexists(String key, String value) {
        return (boolean)redisTemplate.execute(bfexistsScript, Arrays.asList(key), value);
    }

    /**
     * 批量添加元素
     *
     * @param key
     * @param values
     * @return 按序 1表示添加成功，0表示添加失败
     */
    public List<Integer> bfmadd(String key, String... values) {
        return (List<Integer>) redisTemplate.execute(this.generateScript(bfmaddScript, values), Arrays.asList(key), values);
    }

    /**
     * 批量检查元素是否存在（判断为存在时有可能是误判，不存在是一定不存在）
     *
     * @param key
     * @param values
     * @return 按序 1表示存在，0表示不存在
     */
    public List<Integer> bfmexists(String key, String... values) {
        return (List<Integer>) redisTemplate.execute(this.generateScript(bfmexistsScript, values), Arrays.asList(key), values);
    }

    private RedisScript<List> generateScript(String script, String[] values) {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= values.length; i++) {
            if (i != 1) {
                sb.append(",");
            }
            sb.append("ARGV[").append(i).append("]");
        }
        return new DefaultRedisScript<>(String.format(script, sb.toString()), List.class);
    }

    private final String KEY_WEB_CRAWLER = "web:crawler1";

    @Test
    void bloomFilerTest() {
        Boolean hasKey = redisTemplate.hasKey(KEY_WEB_CRAWLER);
        log.info("bloom hasKey:{}", hasKey);
        // 代码创建布隆过滤器不成功时，可以进行手动创建
        /*if (!hasKey) {
            // 不存在的时候  再去初始化
            Boolean bfreserve = bfreserve(KEY_WEB_CRAWLER, 0.01, 10000);
            log.info("bloom bfreserve:{}", bfreserve);
        }*/
        List<Integer> madd = bfmadd(KEY_WEB_CRAWLER, "baidu", "google");
        log.info("bloom bfmadd:{}", madd);

        Boolean baidu = bfexists(KEY_WEB_CRAWLER, "baidu");
        log.info("bloom bfexists baidu:{}", baidu);

        Boolean bing = bfexists(KEY_WEB_CRAWLER, "bing");
        log.info("bloom bfexists bing:{}", bing);
    }
    /************************ 用户签到（BitMap）  **********************/
    /*
        很多APP为了拉动用户活跃度，往往都会做一些活动，比如连续签到领积分/礼包等等
        传统做法：用户每次签到时，往是数据库插入一条签到数据，展示的时候，把本月（或者指定周期）的签到数据获取出来，用于判断用户是否签到、
        以及连续签到情况；此方式，简单，理解容易；

        Redis做法：由于签到数据的关注点就2个：是否签到（0/1）、连续性，因此就完全可以利用BitMap（位图）来实现；
            将一个月的31天，用31个位（4个字节）来表示，偏移量（offset）代表当前是第几天，0/1表示当前是否签到，连续签到只需从右往左校验连续为1的位数；
         由于String类型的最大上限是512M，转换为bit则是2^32个bit位。

        所需命令：
        SETBIT key offset value：向指定位置offset存入一个0或1
        GETBIT key offset：获取指定位置offset的bit值
        BITCOUNT key [start] [end]：统计BitMap中值为1的bit位的数量
        BITFIELD: 操作（查询，修改，自增）BitMap中bit 数组中的指定位置offset的值
        这里最不容易理解的就是：BITFIELD，详情可参考：https://deepinout.com/redis-cmd/redis-bitmap-cmd/redis-cmd-bitfield.html
        而且这部分还必须理解了，否则，该需求的核心部分就没办法理解了；
     */
    //签到的方式一般就两种，按月（周）/ 自定义周期，下面将两种方式的签到全部列举出来，以供大家参考：
    // 按月签到
    @Autowired
    private SignByMonthServiceImpl signByMonthService;

    /**
     * 测试用户按月签到
     */
    @Test
    public void queryMonthSignDay() {
        //模拟用户签到
        for(int i=5;i<19;i++){
            redisTemplate.opsForValue().setBit("UserId:Sign:560:2022-10", i, true);
        }

        System.out.println("560用户今日是否已签到:" + this.signByMonthService.checkSign("560"));
        Map<String, Boolean> stringBooleanMap = this.signByMonthService.querySignedInMonth("560");
        System.out.println("本月签到情况:");
        for (Map.Entry<String, Boolean> entry : stringBooleanMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + (entry.getValue() ? "√" : "-"));
        }
        long countSignedInDayOfMonth = this.signByMonthService.countSignedInDayOfMonth("560");
        System.out.println("本月一共签到:" + countSignedInDayOfMonth + "天");
        System.out.println("目前连续签到:" + this.signByMonthService.queryContinuousSignCount("560", 7) + "天");
    }

    @Autowired
    private SignByRangeServiceImpl signByRangeService;
    /**
     * 指定时间签到
     */
    @Test
    void queryRangeSignTest() {
        DateTimeFormatter isoDateTime = DateTimeFormatter.ISO_DATE_TIME;
        // 活动开始时间
        LocalDateTime start = LocalDateTime.of(2022, 10, 1, 1, 0, 0);
        Integer rangeId = 1;
        Integer userId = 8899;
        log.info("签到开始时间: {}", start.format(isoDateTime));
        log.info("活动ID: {} 用户ID: {}", rangeId, userId);

        // 手动指定偏移量签到
        signByRangeService.sign(rangeId, userId, 0);

        // 判断是否签到
        Boolean signed = signByRangeService.checkSign(rangeId, userId, start);
        log.info("今日是否签到: {}", signed ? "√" : "-");

        // 签到
        Boolean sign = signByRangeService.sign(rangeId, userId, start);
        log.info("签到操作之前的签到状态：{} （-：表示今日第一次签到，√：表示今天已经签到过了）", sign ? "√" : "-");

        // 签到总数
        long countSigned = signByRangeService.countSigned(rangeId, userId);
        log.info("总共签到: {} 天", countSigned);

        // 连续签到的次数
        long continuousSignCount = signByRangeService.queryContinuousSignCount(rangeId, userId, start);
        log.info("连续签到: {} 天", continuousSignCount);

        // 签到的详情
        Map<String, Boolean> stringBooleanMap = signByRangeService.querySigned(rangeId, userId, start);
        for (Map.Entry<String, Boolean> entry : stringBooleanMap.entrySet()) {
            log.info("签到详情> {} : {}", entry.getKey(), (entry.getValue() ? "√" : "-"));
        }
    }
    /************************ GEO搜附近  **********************/
    /*
        很多生活类的APP都具备一个搜索附近的功能，比如美团搜索附近的商家；
        如果自己想要根据经纬度来实现一个搜索附近的功能，是非常麻烦的；但是Redis 在3.2的版本新增了Redis GEO，用于存储地址位置信息，并对支持范围搜索；
        基于GEO就能轻松且快速的开发一个搜索附近的功能；
        geoadd：新增位置坐标
        127.0.0.1:6379> GEOADD drinks 116.62445 39.86206 starbucks 117.3514785 38.7501247 yidiandian 116.538542 39.75412 xicha
(integer) 3
        geopos：获取位置坐标
        127.0.0.1:6379> GEOPOS drinks starbucks
        1) 1) "116.62445157766342163"
        2) "39.86206038535793539"
        geodist：计算两个位置之间的距离。
        单位参数：
        m ：米，默认单位。
        km ：千米。
        mi ：英里。
        ft ：英尺。
        127.0.0.1:6379> GEODIST drinks starbucks yidiandian
        "138602.4133"
        127.0.0.1:6379> GEODIST drinks starbucks xicha m
        "14072.1255"
        georadius：根据用户给定的经纬度坐标来获取指定范围内的地理位置集合。
        GEORADIUS drinks 116 39 100 km WITHDIST
        GEORADIUS drinks 116 39 100 km WITHDIST WITHCOORD
        GEORADIUS drinks 116 39 100 km WITHDIST WITHCOORD WITHHASH
        GEORADIUS drinks 116 39 120 km WITHDIST WITHCOORD  COUNT 1
        GEORADIUS drinks 116 39 120 km WITHDIST WITHCOORD  COUNT 1 ASC
        GEORADIUS drinks 116 39 120 km WITHDIST WITHCOORD  COUNT 1 DESC
            m ：米，默认单位。
            km ：千米。
            mi ：英里。
            ft ：英尺。
            WITHDIST: 在返回位置元素的同时， 将位置元素与中心之间的距离也一并返回。
            WITHCOORD: 将位置元素的经度和纬度也一并返回。
            WITHHASH: 以 52 位有符号整数的形式， 返回位置元素经过原始 geohash 编码的有序集合分值。 这个选项主要用于底层应用或者调试， 实际中的作用并不大。
            COUNT 限定返回的记录数。
            ASC: 查找结果根据距离从近到远排序。
            DESC: 查找结果根据从远到近排序。
        georadiusbymember：根据储存在位置集合里面的某个地点获取指定范围内的地理位置集合。功能和上面的georadius类似，只是georadius是以经纬度坐标为中心，这个是以某个地点为中心；
        geohash：返回一个或多个位置对象的 geohash 值。
        GEOHASH drinks starbucks xicha
     */
    @Autowired
    private GeoServiceImpl geoService;

    @Test
    public void geoTest() {

        geoService.add("starbucks", new Point(116.62445, 39.86206));
        geoService.add("yidiandian", new Point(117.3514785, 38.7501247));
        geoService.add("xicha", new Point(116.538542, 39.75412));

        geoService.get("starbucks", "yidiandian", "xicha");

        GeoResults nearByXY = geoService.getNearByXY(new Point(116, 39), new Distance(120, Metrics.KILOMETERS));
        List<GeoResult> content = nearByXY.getContent();
        for (GeoResult geoResult : content) {
            log.info("{}", geoResult.getContent());
        }

        GeoResults nearByPlace = geoService.getNearByPlace("starbucks", new Distance(120, Metrics.KILOMETERS));
        content = nearByPlace.getContent();
        for (GeoResult geoResult : content) {
            log.info("{}", geoResult.getContent());
        }

        geoService.getGeoHash("starbucks", "yidiandian", "xicha");

        geoService.del("yidiandian", "xicha");
    }
    /************************ 简单限流/全局ID  **********************/
    /*
        为了保证项目的安全稳定运行，防止被恶意的用户或者异常的流量打垮整个系统，一般都会加上限流，比如常见的sential、hystrix，都是实现限流控制；
        如果项目用到了Redis，也可以利用Redis，来实现一个简单的限流功能；
        功能所需命令
        INCR：将 key 中储存的数字值增一
        Expire：设置key的有效期
     */
    @Autowired
    private IncrServiceImpl incrLimitService;

    @Test
    void incrLimitTest () throws Exception{
        String userName = "user1";

        int tag = 1;

        boolean frequency = incrLimitService.frequency(userName);
        log.info("第{}次是否放行：{}", tag, frequency);
        for (int i = 0; i < 100; i++) {
            tag += 1;
            incrLimitService.frequency(userName);
        }
        frequency = incrLimitService.frequency(userName);
        log.info("第{}次是否放行：{}", tag, frequency);

        Thread.sleep(5000);
        frequency = incrLimitService.frequency(userName);
        log.info("模拟等待5s后，第{}次是否放行：{}", tag, frequency);
    }

    @Test
    void globalIDTest () {
        String globalIdKey = "redis:incr:id";
        // 获取全局id
        Long aLong = incrLimitService.globalID(globalIdKey);
        log.info("全局Id:{}",aLong);
    }
    /************************ 简单分布式锁  **********************/
    /*
        在分布式系统中，很多操作是需要用到分布式锁，防止并发操作带来一些问题；因为redis是独立于分布式系统外的其他服务，因此就可以利用redis，
        来实现一个简单的不完美分布式锁；
        功能所需命令:
        SETNX key不存在，设置；key存在，不设置
        set  key  value [ex seconds] [nx | xx]上面的方式，虽然能够加锁，但是不难发现，很容易出现死锁的情况；比如，a用户在加锁之后，突然系统挂了，
        此时a就永远不会释放他持有的锁了，从而导致死锁；为此，我们可以利用redis的过期时间来防止死锁问题

        而且此问题在此方案下并没有完美的解决方案，只能做到尽可能的避免：
        方式一，value设置为随机数（如：1234），在程序释放锁的时候，检测一下是不是自己加的锁；比如，A线程在第8s释放的锁就是线程B加的，
        此时在释放的时候，就可以检验一下value是不是自己当初设置的值（1234），是的就释放，不是的就不管了；
        方式二，只在时间消耗比较小的业务上选用此方案，尽可能的避免执行时间超过锁的自动释放时间
     */


    /************************ 发布/订阅&消息队列  **********************/
    /*
        发布/订阅是比较常用的一种模式；在分布式系统中，如果需要实时感知到一些变化，比如：某些配置发生变化需要实时同步，就可以用到发布，订阅功能
        常用API
            PUBLISH channel message：将消息推送到指定的频道
            SUBSCRIBE channel [channel …]：订阅给定的一个或多个频道的信息
        Redis：轻量级，低延迟，高并发，低可靠性；
     */
    @Test
    void publishRedisTest () {
        // 发布订阅,RedisConfig内配置自动监听 ,只发送不消费，消息在redis内部看不到
        redisTemplate.convertAndSend("chat", "hello " + LocalDateTime.now());
    }

    private final String REDIS_LP_QUEUE = "redis:lp:queue";
    private final String REDIS_RP_QUEUE = "redis:rp:queue";

    /*
        消息队列:往对列添加消息
     */
    @Test
    void messagePushTest () {

        /*
            leftPush 添加到key的左边也就是头部 rightPush 添加到key的右边也就是尾部
            leftPush发消息 leftPop收消息 是后进入先出
            rightPush发消息 leftPop收消息 是先进先出
            leftPush发消息 rightPop收消息 是先进先出
            总结：想要先进先出需要保证发送和接收相反（leftPush-》rightPop）
         */
        redisTemplate.opsForList().leftPush(REDIS_LP_QUEUE,"hello "+ LocalDateTime.now());
        redisTemplate.opsForList().leftPush(REDIS_LP_QUEUE,"hello "+ LocalDateTime.now());

        redisTemplate.opsForList().leftPush(REDIS_RP_QUEUE,"hello "+ LocalDateTime.now());
        redisTemplate.opsForList().leftPush(REDIS_RP_QUEUE,"hello "+ LocalDateTime.now());
    }

    /**
     * 消息队列:后进先出
     */
    @Test
    void leftPopTest () {
        while (true) {
            // 从key的左边也就是头部读取
            Object o = redisTemplate.opsForList().leftPop(REDIS_LP_QUEUE, 0, TimeUnit.SECONDS);
            log.info("先进后出队列 接收到数据：{}", o);
        }
    }

    /**
     * 消息队列:先进先出
     */
    @Test
    void rightPopTest () {
        while (true) {
            // 从key的左边也就是头部读取
            Object o = redisTemplate.opsForList().rightPop(REDIS_LP_QUEUE, 0, TimeUnit.SECONDS);
            log.info("先进先出 接收到数据：{}", o);
        }
    }
    /************************ 商品筛选(SADD/SINTER )  **********************/
    /*
        场景：商城类的应用，都会有类似于下图的一个商品筛选的功能，来帮用户快速搜索理想的商品；
        假如现在iphone 100 、华为mate 5000 已发布，在各大商城上线；下面就通过 Redis 的 set 来实现上述的商品筛选功能；
        功能所需命令
            SADD key member [member …]：添加一个或多个元素
            SINTER key [key …]：返回给定所有集合的交集
     */
    // 男性
    private String HE_SEX = "he:sex";
    // 大眼睛
    private String BIG_EYE = "big:eye";

    @Test
    void sinterTest () {
        String[] heSex = {"xiaoming","xiaoli"};
        redisTemplate.opsForSet().add(HE_SEX, heSex);
        String[] bigEye = {"xiaowang","xiaoming"};
        redisTemplate.opsForSet().add(BIG_EYE, bigEye);

        // 取两个集合交集
        Set intersect = redisTemplate.opsForSet().intersect(HE_SEX, BIG_EYE);
        intersect.stream().forEach(inter -> System.out.println(inter));
    }
    /************************ 购物车(HSET/HMSET/HGET/HGETALL/HINCRBY/HLEN)  **********************/
    /*
        商品缓存
        电商项目中，商品消息，都会做缓存处理，特别是热门商品，访问用户比较多，由于商品的结果比较复杂，店铺信息，产品信息，标题、描述、详情图，
        封面图；为了方便管理和操作，一般都会采用 Hash 的方式来存储（key为商品ID，field用来保存各项参数，value保存对于的值）
        购物车
        当商品信息做了缓存，购物车需要做的，就是通过Hash记录商品ID，以及需要购买的数量（其中key为用户信息，field为商品ID，value用来记录购买的数量） ；
        功能所需命令:
            HSET key field value : 将哈希表 key 中的字段 field 的值设为 value ;
            HMSET key field1 value1 [field2 value2 ] ：同时将多个 field-value (域-值)对设置到哈希表 key 中。
            HGET key field：获取存储在哈希表中指定字段的值。
            HGETALL key ：获取在哈希表中指定 key 的所有字段和值
            HINCRBY key field increment ：为哈希表 key 中的指定字段的整数值加上增量 increment 。
            HLEN key：获取哈希表中字段的数量
     */

    @Test
    void hsetTest () {
        String SHOPPING_CAR = "shopping:car";
        // 添加单个商品
        redisUtil.hset(SHOPPING_CAR,"c001" ,1);
        Map<Object, Object> map = new HashMap<>();
        map.put("c002",1);
        map.put("c003",2);
        boolean hmset = redisUtil.hmset(SHOPPING_CAR, map);

        // 添加商品购买数量
        double c002 = redisUtil.hincr(SHOPPING_CAR, "c002", 1);
        log.info("添加商品购买数量后：{}",c002);
        // 减少商品的数量
        double c003 = redisUtil.hdecr(SHOPPING_CAR, "c003", 1);
        log.info("减少商品购买数量后：{}",c003);

        // 获取单个商品购买的数量
        Object c0021 = redisUtil.hget(SHOPPING_CAR, "c002");
        log.info("商品c002购买数量：{}",c0021);

        // 获取购物车商品数量
        Long hlen = redisUtil.hlen(SHOPPING_CAR);
        log.info("获取购物车的商品数量:{}",hlen);

        // 购物车详情
        Map<Object, Object> objectObjectMap = redisUtil.hGetAll(SHOPPING_CAR);
        log.info("Map:{}", JSONUtil.toJsonStr(objectObjectMap));
    }
    /************************ 物流信息（时间线）  **********************/
    /*
        寄快递、网购的时候，查询物流信息，都会给我们展示xxx时候，快递到达什么地方了，这就是一个典型的时间线列表；
        数据库的做法，就是每次变更就插入一条带时间的信息记录，然后根据时间和ID（ID是必须的，如果出现两个相同的时间，单纯时间排序，会造成顺序不对），来排序生成时间线；

        我们也可以通过 Redis 的 List 来实现时间线功能，由于 List 采用的是双向链表，因此升序，降序的时间线都能正常满足；

            RPUSH key value1 [value2]：在列表中添加一个或多个值，（升序时间线）
            LPUSH key value1 [value2]：将一个或多个值插入到列表头部（降序时间线）
            LRANGE key start stop：获取列表指定范围内的元素
     */
    @Test
    void logisticsLineTest () {
        // 时间线升序
        String TIME_LINE_ASC = "time:line:asc";
        // 时间线降序
        String TIME_LINE_DESC = "time:line:desc";

        // 升序 (从队列尾部开始添加)
        redisUtil.lRightPush(TIME_LINE_ASC, 20220805170000l);
        redisUtil.lRightPush(TIME_LINE_ASC, 20220805170001l);
        redisUtil.lRightPush(TIME_LINE_ASC, 20220805170002l);
        // lRang并不会使得元素出队列，如果使用leftPop或者rightPop会使得对象出队列
        List<Object> ascObjects = redisUtil.lRange(TIME_LINE_ASC, 0, -1);
        log.info("时间线升序：{}", JSONUtil.toJsonStr(ascObjects));
        // 降序 (从队列头部开始添加)
        redisUtil.lLeftPush(TIME_LINE_DESC, 20220805170000l);
        redisUtil.lLeftPush(TIME_LINE_DESC, 20220805170001l);
        redisUtil.lLeftPush(TIME_LINE_DESC, 20220805170002l);
        List<Object> descObjects = redisUtil.lRange(TIME_LINE_DESC, 0, -1);
        log.info("时间线降序：{}", JSONUtil.toJsonStr(descObjects));
    }
}
