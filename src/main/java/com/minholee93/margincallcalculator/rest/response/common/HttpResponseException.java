package com.minholee93.margincallcalculator.rest.response.common;

import com.minholee93.margincallcalculator.rest.response.common.code.HttpResponseCode;

public class HttpResponseException extends RuntimeException {

    private final HttpResponseCode httpResponseCode;

    public HttpResponseException(HttpResponseCode httpResponseCode) {
        super();
        this.httpResponseCode = httpResponseCode;
    }

    public int getHttpStatusValue() {
        return httpResponseCode.getHttpStatusValue();
    }

    public String getResponseCode() {
        return httpResponseCode.getResponseCode();
    }

    public HttpResponseCode getHttpResponseCode() {
        return httpResponseCode;
    }
}
