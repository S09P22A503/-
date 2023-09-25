package com.sshmarket.trade.application.redis;

import com.sshmarket.trade.application.ModifyLastMessageUseCase;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RedisProvider {

    private final String keySet = "trade";
    private final RedisTemplate<String, String> redisTemplate;
    private final ModifyLastMessageUseCase modifyLastMessageUseCase;
    private final String TRADE_ID = "tradeId";
    private final String USER_ID = "userId";


    public void saveConnectionUser(Long tradeId, Long userId, String sessionId) {
        redisTemplate.opsForSet().add(keySet + tradeId, String.valueOf(userId));
        HashOperations<String, Object, Object> hashOperations = redisTemplate.opsForHash();
        Map<String, String> map = new HashMap<>();
        map.put(TRADE_ID, tradeId.toString());
        map.put(USER_ID, userId.toString());
        hashOperations.putAll(sessionId, map);
    }

    public void deleteConnectionUser(String sessionId) {
        String tradeId = String.valueOf(redisTemplate.opsForHash().get(sessionId, TRADE_ID));
        String userId = String.valueOf(redisTemplate.opsForHash().get(sessionId, USER_ID));
        // DISCONNECT가 두번 호출됨
        if (tradeId.equals("null"))
            return;
        redisTemplate.opsForSet().remove(keySet + tradeId, userId);
        redisTemplate.delete(sessionId);
        modifyLastMessageUseCase.modifyLastMessage(Long.valueOf(tradeId), Long.valueOf(userId));
    }

}