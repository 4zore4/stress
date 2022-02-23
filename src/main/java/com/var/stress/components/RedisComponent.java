package com.var.stress.components;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Slf4j
@Component
public class RedisComponent {
    private static final Logger logger = LoggerFactory.getLogger( RedisComponent.class);

    @Autowired
    private JedisPool jedisPool;

    public boolean increment(String key){
        Jedis jedis = jedisPool.getResource();
        long result = 0;
        try {
            jedis.incr(key);
            return true;
        }catch (Exception e){
            logger.info(String.valueOf(e));
        }
        return false;

    }
}
