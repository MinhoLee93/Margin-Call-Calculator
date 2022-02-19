package com.minholee93.margincallcalculator.rest.response.common;

import com.minholee93.margincallcalculator.rest.response.common.code.HttpResponseCode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ElementKind;
import javax.validation.Path;
import java.util.stream.Collectors;

@FunctionalInterface
interface ExceptionResponseMessageCreator<T extends Exception> {

    String createMessage(T exception, HttpResponseCode httpResponseCode, ResponseMessageReader responseMessageReader);

    // NOTE : response.error 에 정의되어 있는 에러 메시지 사용
    ExceptionResponseMessageCreator<Exception> DEFAULT_MESSAGE_CREATOR =
            (exception, httpResponseCode, responseMessageReader) ->
                    responseMessageReader.getMessage(httpResponseCode.getHttpStatus(), httpResponseCode.getMessageSuffixKey());

    // NOTE : Spring @Validated 에서 validation 실패한 필드명 및 정의된 default message return
    ExceptionResponseMessageCreator<MethodArgumentNotValidException> SPRING_JSR303_MESSAGE_CREATOR =
            (ex, httpResponseCode, responseMessageReader) ->
                    ex.getBindingResult()
                            .getAllErrors()
                            .stream()
                            .map(objectError -> {
                                String fieldName =
                                        (objectError instanceof FieldError) ? ((FieldError) objectError).getField() : objectError.getObjectName();
                                return (StringUtils.isNotBlank(fieldName) ? fieldName + " - " : "") + objectError.getDefaultMessage();
                            })
                            .collect(Collectors.joining(" "));

    // NOTE : javax.validation 에서 validation 실패한 필드명 및 정의된 default message return
    ExceptionResponseMessageCreator<ConstraintViolationException> JSR303_MESSAGE_CREATOR =
            (ex, httpResponseCode, responseMessageReader) ->
                    ex.getConstraintViolations()
                            .stream()
                            .map(constraintViolation -> {
                                String fieldName = determineField(constraintViolation);
                                return (StringUtils.isNotBlank(fieldName) ? fieldName + " - " : "") + constraintViolation.getMessage();
                            })
                            .collect(Collectors.joining(" "));

    static <T> String determineField(ConstraintViolation<T> violation) {

        Path path = violation.getPropertyPath();
        StringBuilder sb = new StringBuilder();
        boolean first = true;

        for (Path.Node node : path) {

            if (node.isInIterable()) {
                sb.append('[');
                Object index = node.getIndex();
                if (index == null) {
                    index = node.getKey();
                }
                if (index != null) {
                    sb.append(index);
                }
                sb.append(']');
            }

            String name = node.getName();
            if (name != null &&
                    (node.getKind() == ElementKind.PROPERTY || node.getKind() == ElementKind.PARAMETER) && !name.startsWith("<")) {

                if (!first) {
                    sb.append('.');
                }
                first = false;
                sb.append(name);
            }
        }

        return sb.toString();
    }
}
