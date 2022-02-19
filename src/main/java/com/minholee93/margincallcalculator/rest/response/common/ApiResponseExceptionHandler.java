package com.minholee93.margincallcalculator.rest.response.common;

import com.minholee93.margincallcalculator.rest.response.common.code.HttpResponseCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;

import static com.minholee93.margincallcalculator.rest.constant.CommonConstant.DOMAIN_CODE;
import static com.minholee93.margincallcalculator.rest.response.common.ExceptionResponseMessageCreator.*;

@Slf4j
@RequiredArgsConstructor
@RestControllerAdvice
public class ApiResponseExceptionHandler extends ResponseEntityExceptionHandler {

    private final ResponseMessageReader responseMessageReader;

    // NOTE : spring validation 실패를 다룸
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {

        return handle(ex, HttpResponseCode.from(HttpStatus.BAD_REQUEST), SPRING_JSR303_MESSAGE_CREATOR);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex,
                                                             Object body,
                                                             HttpHeaders headers,
                                                             HttpStatus status,
                                                             WebRequest request) {

        if (status.is5xxServerError()) {
            log.error("Internal Server Error Occurred!", ex);
        }

        return handle(ex, HttpResponseCode.from(status), DEFAULT_MESSAGE_CREATOR);
    }

    // NOTE : javax.validation 실패를 다룸
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex) {

        return handle(ex, HttpResponseCode.from(HttpStatus.BAD_REQUEST), JSR303_MESSAGE_CREATOR);
    }

    // NOTE : 내부적으로 정의된 UncheckedException 을 다룸
    @ExceptionHandler(HttpResponseException.class)
    public ResponseEntity<Object> handleHttpResponseException(HttpResponseException ex) {

        return handle(ex, ex.getHttpResponseCode(), DEFAULT_MESSAGE_CREATOR);
    }

    // NOTE : 정의하지 않은 에러를 다룸
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleUncaughtException(Exception ex) {
        return handleExceptionInternal(ex, null, null, HttpStatus.INTERNAL_SERVER_ERROR, null);
    }

    private <T extends Exception> ResponseEntity<Object> handle(T ex,
                                                                HttpResponseCode httpResponseCode,
                                                                ExceptionResponseMessageCreator<T> messageCreator) {

        return new ResponseEntity<>(
                ApiResponse.of(
                        createErrorCode(httpResponseCode),
                        messageCreator.createMessage(ex, httpResponseCode, responseMessageReader)
                ),
                httpResponseCode.getHttpStatus()
        );
    }

    private String createErrorCode(HttpResponseCode httpResponseCode) {

        return httpResponseCode.getHttpStatusValue() + DOMAIN_CODE + httpResponseCode.getResponseCode();
    }
}


