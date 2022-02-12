package com.minholee93.margincallcalculator.rest.response;

import lombok.Getter;
import lombok.ToString;
import org.slf4j.MDC;

@Getter
@ToString
public class ApiResponse<T> {

    private final String code;
    private final String message;

    private final T data;
    private final String transactionId;

    private ApiResponse(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.transactionId = MDC.get("transactionId");
    }

    public static <T> ApiResponse<T> of(String code, String message) {
        return new ApiResponse<>(code, message, null);
    }

    public static <T> ApiResponse<T> of(String code, String message, T data) {
        return new ApiResponse<>(code, message, data);
    }
}
