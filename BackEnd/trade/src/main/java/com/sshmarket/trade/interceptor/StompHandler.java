package com.sshmarket.trade.interceptor;

import com.sshmarket.trade.application.redis.RedisProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.stereotype.Component;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE + 99)
@RequiredArgsConstructor
public class StompHandler implements ChannelInterceptor {

    private final RedisProvider redisProvider;

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);

        // 토큰 검증 로직 필요
        handleMessage(accessor.getCommand(), accessor);
        return message;
    }

    private void handleMessage(StompCommand stompCommand, StompHeaderAccessor accessor) {
        switch (stompCommand) {
            case CONNECT:
                // 연결 로직
            case DISCONNECT:
                // 해제 로직

        }
    }


}
