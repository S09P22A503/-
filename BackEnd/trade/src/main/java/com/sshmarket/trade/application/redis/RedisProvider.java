package com.sshmarket.trade.application.redis;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RedisProvider {

    private final String keySet = "trade";
    private final RedisTemplate<String, String> redisTemplate;


    public void saveConnectionUser(Long tradeId, Long userId) {
        redisTemplate.opsForSet().add(keySet + tradeId, String.valueOf(userId));
    }

    public void deleteConnectionUser(Long tradeId, Long userId) {
        redisTemplate.opsForSet().remove(keySet + tradeId, String.valueOf(userId));
    }

}