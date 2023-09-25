package com.sshmarket.trade.interceptor;

import com.sshmarket.trade.application.redis.RedisProvider;
import com.sshmarket.trade.infra.jwt.JwtTranslator;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class StompHandler implements ChannelInterceptor {

    private final JwtTranslator jwtTranslator;
    private final RedisProvider redisProvider;

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);
        handleMessage(accessor.getCommand(), accessor);
        return message;

    }

    private String getAccessToken(StompHeaderAccessor accessor) {

        return accessor.getFirstNativeHeader("Authorization");
    }


    private void handleMessage(StompCommand stompCommand, StompHeaderAccessor accessor) {
        switch (stompCommand) {
            case CONNECT:
                // 연결 로직
                redisProvider.saveConnectionUser(getChatNumber(accessor),
                        jwtTranslator.getUserId(getAccessToken(accessor)), accessor.getSessionId());
                break;
            case DISCONNECT:
                // 해제 로직
                redisProvider.deleteConnectionUser(accessor.getSessionId());
                break;
        }
    }

    private Long getChatNumber(StompHeaderAccessor accessor) {
        return Long.valueOf(Objects.requireNonNull(accessor.getFirstNativeHeader("chatNumber")));
    }

}
