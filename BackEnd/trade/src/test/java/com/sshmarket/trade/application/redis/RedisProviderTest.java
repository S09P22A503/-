package com.sshmarket.trade.application.redis;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
class RedisProviderTest {

    @Autowired
    RedisProvider redisProvider;

    @Autowired
    RedisTemplate<String, String> redisTemplate;

    @Test
    public void Redis_값_저장_테스트(){
        redisProvider.saveConnectionUser(1L, 1L);

        for (String member : redisTemplate.opsForSet().members(String.valueOf(1L))) {
            System.out.println("member = " + member);
            Assertions.assertThat(member).isEqualTo("1");
        }

    }
}