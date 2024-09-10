package com.roman.decision_cloud_stream.domain;


import com.roman.decision_cloud_stream.domain.enumerated.State;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;

@Entity
@NoArgsConstructor
@Getter
public class Decision {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private State state;
    private SSN ssn;

    private Decision(State state, SSN ssn) {
        this.state = state;
        this.ssn = ssn;
    }
    public static Decision decide(SSN ssn, LocalDate birthDate){
        Period creditDuration = Period.ofYears(40);
        var maximumCustomerYears = 70;

        LocalDate maximumAllowedAge = LocalDate.now().plus(creditDuration);

        long customerAgeAfterCredit = ChronoUnit.YEARS.between(birthDate, maximumAllowedAge);
        if(customerAgeAfterCredit > maximumCustomerYears){
            return new Decision(State.REJECTED, ssn);
        } else if(ssn.ssn() % 2 == 0){
            return new Decision(State.APPROVED, ssn);
        }

        return new Decision(State.PRE_APPROVED, ssn);
    }
}
