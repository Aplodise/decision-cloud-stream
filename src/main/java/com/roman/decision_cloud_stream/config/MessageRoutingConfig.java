package com.roman.decision_cloud_stream.config;


import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.function.context.MessageRoutingCallback;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

@Configuration
@Slf4j
public class MessageRoutingConfig {
    private static final String EVENT_TYPE = "X-EVENT-TYPE";

    private Map<String, String> routingFunctions = Map.of("CustomerCreated", "handleCustomerCreated",
                                                            "EmailChanged", "handleEmailChanged");

    @Bean
    public MessageRoutingCallback routingCallback(){
        return new MessageRoutingCallback() {
            @Override
            public String routingResult(Message<?> message) {
                String eventType = (String) message.getHeaders().get(EVENT_TYPE);
                log.info("The header type is: {}", eventType);
                String routeTo = routingFunctions.getOrDefault(eventType, "unknownEvent");
                log.info("-------------> routing the event to: {}", routeTo);
                return routeTo;
            }
        };
    }

    @Bean
    public Consumer<Message<?>> unknownEvent(){
        return message -> {
            log.warn("Unknown event, nothing happens!");
        };
    }
}
