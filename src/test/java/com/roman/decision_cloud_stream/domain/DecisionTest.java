package com.roman.decision_cloud_stream.domain;

import com.roman.decision_cloud_stream.domain.enumerated.State;
import org.junit.jupiter.api.Test;


import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class DecisionTest {

    @Test
    void shouldRejectWhenAgeIsGreaterThan70AfterEndOfTheCredit(){
        var ssn = SSN.of(234563324);
        var birthDate = LocalDate.of(1990, 12, 25);

        Decision decision = Decision.decide(ssn, birthDate);

        assertNotNull(decision);
        assertEquals(State.REJECTED, decision.getState());
    }
    @Test
    void shouldApproveIfSsnIsEven(){
        var ssn = SSN.of(222222222);
        var birthDate = LocalDate.of(1995, 12, 25);

        Decision decision = Decision.decide(ssn, birthDate);

        assertNotNull(decision);
        assertEquals(State.APPROVED, decision.getState());
    }
}