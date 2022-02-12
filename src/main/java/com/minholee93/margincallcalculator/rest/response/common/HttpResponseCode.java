package com.minholee93.margincallcalculator.rest.response.common;

import org.springframework.http.HttpStatus;

public interface HttpResponseCode {

    HttpStatus getHttpStatus();

    String getResponseCode();

    default String getMessageSuffixKey() {
        return getHttpStatus().name();
    }

    default int getHttpStatusValue() {
        return getHttpStatus().value();
    }
}
