package com.roman.decision_cloud_stream.service;

import java.time.LocalDate;

public interface DecisionMakerService {
    void decide(Integer ssn, LocalDate birthDate);
}
