package com.roman.decision_cloud_stream.exception;

import lombok.Value;

@Value
public class RetryableException extends RuntimeException{
    String reason;
}
