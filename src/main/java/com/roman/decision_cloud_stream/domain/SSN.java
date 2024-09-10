package com.roman.decision_cloud_stream.domain;

import jakarta.persistence.Embeddable;
import org.springframework.util.Assert;

import java.util.Objects;

@Embeddable
public record SSN(Integer ssn) {
    public SSN {
        Objects.requireNonNull(ssn, "SSN could not be null");
        Assert.isTrue(String.valueOf(ssn).toCharArray().length==9, "The ssn must have 9 characters");
    }
    public static SSN of(Integer ssn){
        return new SSN(ssn);
    }
}
