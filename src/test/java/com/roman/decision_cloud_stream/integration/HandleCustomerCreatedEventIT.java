package com.roman.decision_cloud_stream.integration;

import com.roman.decision_cloud_stream.domain.Decision;
import com.roman.decision_cloud_stream.repository.DecisionRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.StubFinder;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;

import java.util.Optional;

@SpringBootTest
@AutoConfigureStubRunner(
        stubsMode = StubRunnerProperties.StubsMode.LOCAL,
        ids = "com.roman:customer-cloud-stream:+:stubs"
)
@Slf4j
public class HandleCustomerCreatedEventIT {
    @Autowired
    private StubFinder stubFinder;
    @Autowired
    private DecisionRepository decisionRepository;

    @Test
    void handleEvent(){
        this.stubFinder.trigger("shouldPublishCustomerCreated");

        Optional<Decision> decision = this.decisionRepository.findAll()
                .stream().findAny();
        Assertions.assertTrue(decision.isPresent());
    }
}
