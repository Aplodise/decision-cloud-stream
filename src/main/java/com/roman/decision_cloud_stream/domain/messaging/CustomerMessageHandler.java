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

import java.util.function.Consumer;
import java.util.function.Function;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class CustomerMessageHandler {
    private final DecisionMakerService decisionMakerService;

//    @Bean
//    public Consumer<CustomerEvent.CustomerCreated> handleCustomerCreated(){
//        return this::handle;
//    }

    private void handle(CustomerEvent.CustomerCreated customerCreated){
        log.info("consuming the event: {}", customerCreated);
        CustomerDTO customerDTO = customerCreated.customer();
        decisionMakerService.decide(customerDTO.ssn(), customerDTO.birthDate());
    }
    @Bean
    public Function<CustomerEvent.CustomerCreated, Decision> processCustomerCreated(){
        return customerCreated -> {
            log.info("processing (transforming) the event: {}", customerCreated);
            CustomerDTO customerDTO = customerCreated.customer();
            if(customerDTO.firstName().startsWith("N")) {
                throw new IllegalStateException("The customer is invalid");
            } else if (customerDTO.firstName().startsWith("F")) {
                throw new RetryableException("This exception is retryable");
            }
            Decision decision = decisionMakerService.decide(customerDTO.ssn(), customerDTO.birthDate());
            log.info("producing the decision: {}", decision);
            return decision;
        };
    }
}
