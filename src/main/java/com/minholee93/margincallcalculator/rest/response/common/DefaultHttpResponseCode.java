package com.minholee93.margincallcalculator.rest.response.common;

import com.minholee93.margincallcalculator.rest.response.common.code.HttpResponseCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Getter
@ToString
public class DefaultHttpResponseCode implements HttpResponseCode {

    private final HttpStatus httpStatus;

    public DefaultHttpResponseCode(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public static DefaultHttpResponseCode from(HttpStatus httpStatus) {
        return new DefaultHttpResponseCode(httpStatus);
    }

    @Override
    public HttpStatus getHttpStatus() {
        return this.httpStatus;
    }

    @Override
    public String getResponseCode() {
        return "99";
    }
}
