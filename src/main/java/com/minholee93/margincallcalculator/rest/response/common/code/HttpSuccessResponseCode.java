package com.minholee93.margincallcalculator.rest.response.common.code;

import org.springframework.http.HttpStatus;

public enum HttpSuccessResponseCode implements HttpResponseCode {
    OK {
        @Override
        public HttpStatus getHttpStatus() {
            return HttpStatus.OK;
        }

        @Override
        public String getResponseCode() {
            return "00";
        }
    },
    NO_CONTENT {
        @Override
        public HttpStatus getHttpStatus() {
            return HttpStatus.NO_CONTENT;
        }

        @Override
        public String getResponseCode() {
            return "01";
        }
    }
}
