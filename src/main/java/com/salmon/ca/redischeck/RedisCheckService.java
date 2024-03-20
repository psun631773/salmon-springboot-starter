package com.salmon.ca.redischeck;

import com.salmon.ca.properties.SalmonEsbRocketMQProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;


public class RedisCheckService {
    @Autowired
    private SalmonEsbRocketMQProperties properties;


    private final RedisTemplate<String, String> redisTemplate;

    @Autowired
    public RedisCheckService(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    // 将 messageId 存入 Redis，并设置过期时间为 24 小时
    public void saveMessageId(String messageId) {
        String key = "rocket:" + messageId; // Adding "message:" as a prefix
        redisTemplate.opsForValue().set(key, "salmon-esb-consumed", properties.getRedisRefreshInterval(), TimeUnit.HOURS);

    }

    // 检查 messageId 是否已经消费过
    public boolean isMessageIdConsumed(String messageId) {
        String key = "rocket:" + messageId; // Adding "message:" as a prefix
        return redisTemplate.hasKey(key);

    }
}
