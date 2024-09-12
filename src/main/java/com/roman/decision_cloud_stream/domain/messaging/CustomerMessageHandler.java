package com.roman.decision_cloud_stream.domain.messaging;

import com.roman.decision_cloud_stream.domain.Decision;
import com.roman.decision_cloud_stream.domain.messaging.event.CustomerDTO;
import com.roman.decision_cloud_stream.domain.messaging.event.CustomerEvent;
import com.roman.decision_cloud_stream.exception.RetryableException;
import com.roman.decision_cloud_stream.service.DecisionMakerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import java.util.function.Consumer;


@Configuration
@Slf4j
@RequiredArgsConstructor
public class CustomerMessageHandler {
    private final DecisionMakerService decisionMakerService;

    @Bean
    public Consumer<Message<CustomerEvent.CustomerCreated>> handleCustomerCreated(){
        return customerCreatedMessage -> {
            log.info("[customerCreated] messsageHandler is handling of type -----------> {}", customerCreatedMessage.getHeaders().get("X-EVENT-TYPE"));
            log.info("The message is: {}", customerCreatedMessage.getPayload());
            decisionMakerService.decide(customerCreatedMessage.getPayload().customer().ssn(), customerCreatedMessage.getPayload().customer().birthDate());
        };
    }

    @Bean
    public Consumer<Message<CustomerEvent.EmailChanged>> handleEmailChanged(){
        return emailChangedMessage -> {
            log.info("[customerCreated] messsageHandler is handling of type -----------> {}", emailChangedMessage.getHeaders().get("X-EVENT-TYPE"));
            log.info("The message is: {}", emailChangedMessage.getPayload());
        };
    }







}
