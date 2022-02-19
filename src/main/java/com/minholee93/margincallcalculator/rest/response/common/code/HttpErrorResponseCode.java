package com.minholee93.margincallcalculator.rest.response.common.code;

import org.springframework.http.HttpStatus;

public enum HttpErrorResponseCode implements HttpResponseCode {

    BAD_REQUEST {
        @Override
        public HttpStatus getHttpStatus() {
            return HttpStatus.BAD_REQUEST;
        }

        @Override
        public String getResponseCode() {
            return "00";
        }
    },
    FORBIDDEN {
        @Override
        public HttpStatus getHttpStatus() {
            return HttpStatus.FORBIDDEN;
        }

        @Override
        public String getResponseCode() {
            return "00";
        }
    },
    NOT_FOUND {
        @Override
        public HttpStatus getHttpStatus() {
            return HttpStatus.NOT_FOUND;
        }

        @Override
        public String getResponseCode() {
            return "00";
        }
    },
    METHOD_NOT_ALLOWED {
        @Override
        public HttpStatus getHttpStatus() {
            return HttpStatus.METHOD_NOT_ALLOWED;
        }

        @Override
        public String getResponseCode() {
            return "00";
        }
    },
    UNSUPPORTED_MEDIA_TYPE {
        @Override
        public HttpStatus getHttpStatus() {
            return HttpStatus.UNSUPPORTED_MEDIA_TYPE;
        }

        @Override
        public String getResponseCode() {
            return "00";
        }
    },
    INTERNAL_SERVER_ERROR {
        @Override
        public HttpStatus getHttpStatus() {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }

        @Override
        public String getResponseCode() {
            return "00";
        }
    };
}
