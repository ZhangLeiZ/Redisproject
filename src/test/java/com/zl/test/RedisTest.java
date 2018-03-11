package com.zl.test;

import com.zl.common.RedisUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;

import java.io.IOException;
import java.util.*;

/**
 * Created by Administrator on 2018/3/4.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/config/spring-redis.xml")
public class RedisTest {

    private static final String[] STRCLUSTER = {
            "127.0.0.1:6380",
            "127.0.0.1:6381",
            "127.0.0.1:6382",
            "127.0.0.1:6383",
            "127.0.0.1:6384",
            "127.0.0.1:6385",
    };

    @Autowired
    private RedisUtil redisUtil;
    @Autowired
   private RedisTemplate<String,Object> redisTemplate;
    @Test
    public void JedisTest() {
        Jedis jedis = new Jedis("localhost",6379);
        try {
            System.out.println(jedis.ping());
            System.out.println("连接成功！");
            jedis.set("name","xiaoliuzhegeshamao");
            String str = "你好！";
            jedis.set("hello",str);
        }catch(Exception e) {
            e.printStackTrace();
        }finally{
            jedis.close();
        }

    }

    @Test
    public void RedisCluster()throws IOException {
        Set<HostAndPort> nodes = new HashSet<>();
        for (String str: STRCLUSTER) {
            String[] strs = str.split(":");
            nodes.add(new HostAndPort(strs[0],Integer.valueOf(strs[1])));
        }
        JedisCluster jedisCluster = new JedisCluster(nodes);
        jedisCluster.set("zhang","wwwwwwwwwwwwwwwwwwww");
        System.out.println(jedisCluster.get("zhang"));
        System.out.println(jedisCluster.get("key1"));
        jedisCluster.close();
    }

    @Test
    public void JedisTest2() {
      /*Redis redis1 = new Redis(1,"liushuhua");
      Redis redis2 = new Redis(2,"zhanglei");
     List<Redis> redisLists = Arrays.asList(redis1,redis2);
     redisTemplate.opsForList().leftPushAll("rediset",redisLists);
        List<Redis> redisList = (List<Redis>)redisTemplate.opsForList().leftPop("rediset");*/
      redisTemplate.opsForValue().set("pss","qwerty");
        System.out.println(redisTemplate.opsForValue().get("pss"));

     }

}

