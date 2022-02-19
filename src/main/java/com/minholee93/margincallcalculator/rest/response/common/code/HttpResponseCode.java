package com.minholee93.margincallcalculator.rest.response.common.code;

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

    static HttpResponseCode from(HttpStatus httpStatus) {
        return new HttpResponseCode() {
            @Override
            public HttpStatus getHttpStatus() {
                return httpStatus;
            }

            @Override
            public String getResponseCode() {
                return "99";
            }

            @Override
            public String getMessageSuffixKey() {
                return getHttpStatus().name();
            }
        };
    }
}
