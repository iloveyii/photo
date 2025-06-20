package com.learn.photo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/topic");
        registry.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws")
                .setAllowedOriginPatterns(
                        "http://localhost:*",       // Local dev
                        "https://spring.softhem.net",  // Production domain
                        "http://app:8080",     // Docker service name
                        "http://192.168.*.*",       // Internal network
                        "http://10.*.*.*",       // Internal network
                        "http://172.*.*.*",      // Internal network
                        "https://spring.softhem.net:8080",  // Production domain
                        "http://192.168.*.*:8080",       // Internal network
                        "http://10.*.*.*:8080",       // Internal network
                        "http://172.*.*.*:8080"      // Internal network,
                        "http://127.*.*.*:8080"      // Internal network,
                        "http://127.*.*.*"      // Internal network,

                )
                .withSockJS();
    }


}
