package com.roman.decision_cloud_stream.service;

import com.roman.decision_cloud_stream.domain.Decision;
import com.roman.decision_cloud_stream.domain.SSN;
import com.roman.decision_cloud_stream.repository.DecisionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Slf4j
public class DecisionMakerServiceImpl implements DecisionMakerService {

    private final DecisionRepository decisionRepository;

    @Override
    public Decision decide(Integer ssn, LocalDate birthDate) {
        Decision decision = Decision.decide(SSN.of(ssn), birthDate);
        Decision savedDecision = decisionRepository.save(decision);
        log.info("The decision is: {}", savedDecision.getState());
        return savedDecision;
    }
}
