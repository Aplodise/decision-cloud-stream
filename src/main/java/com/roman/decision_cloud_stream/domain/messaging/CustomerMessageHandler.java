package com.roman.decision_cloud_stream.domain.messaging;

import com.roman.decision_cloud_stream.domain.messaging.event.CustomerDTO;
import com.roman.decision_cloud_stream.domain.messaging.event.CustomerEvent;
import com.roman.decision_cloud_stream.service.DecisionMakerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;
@Configuration
@Slf4j
@RequiredArgsConstructor
public class CustomerMessageHandler {
    private final DecisionMakerService decisionMakerService;

    @Bean
    public Consumer<CustomerEvent.CustomerCreated> handleCustomerCreated(){
        return customerCreated -> {
            log.info("consuming the event: {}", customerCreated);
            CustomerDTO customerDTO = customerCreated.customer();
            decisionMakerService.decide(customerDTO.ssn(), customerDTO.birthDate());
        };
    }
}
