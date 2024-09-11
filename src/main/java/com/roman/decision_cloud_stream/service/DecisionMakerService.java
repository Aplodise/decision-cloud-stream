package com.roman.decision_cloud_stream.service;

import com.roman.decision_cloud_stream.domain.Decision;

import java.time.LocalDate;

public interface DecisionMakerService {
    Decision decide(Integer ssn, LocalDate birthDate);
}
