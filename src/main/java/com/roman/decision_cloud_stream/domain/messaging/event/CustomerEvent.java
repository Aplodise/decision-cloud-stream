package com.roman.decision_cloud_stream.domain.messaging.event;



import java.io.Serializable;
import java.time.Instant;

public sealed interface CustomerEvent extends Serializable {

    record CustomerCreated(Long customerId, Instant createdAt, CustomerDTO customer) implements CustomerEvent{

    }

}
