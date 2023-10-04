package com.sshmarket.trade.infra.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
@RequiredArgsConstructor
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {


    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry
<<<<<<< HEAD
                .addEndpoint("/trade")
                .setAllowedOrigins("*");
//                .setAllowedOriginPatterns("*")
//                .withSockJS();
=======
                .addEndpoint("/trades/trade")
                .setAllowedOriginPatterns("*")
                .withSockJS();
>>>>>>> d055418397d27c40756c4cac869d4f8c85f3a7c8
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/sub");
        registry.setApplicationDestinationPrefixes("/pub");
    }

}
