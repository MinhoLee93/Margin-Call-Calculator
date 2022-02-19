package com.minholee93.margincallcalculator.rest.response.common;

import com.minholee93.margincallcalculator.rest.response.common.code.HttpResponseCode;
import com.minholee93.margincallcalculator.rest.response.common.code.HttpSuccessResponseCode;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.lang.NonNull;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import static com.minholee93.margincallcalculator.rest.constant.CommonConstant.DOMAIN_CODE;

@RestControllerAdvice(annotations = RestController.class)
@RequiredArgsConstructor
public class ApiResponseBodyAdvice implements ResponseBodyAdvice<Object> {

    private final ResponseMessageReader responseMessageReader;

    private static final Dummy DUMMY = new Dummy();

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return AbstractJackson2HttpMessageConverter.class.isAssignableFrom(converterType);
    }

    @Override
    public Object beforeBodyWrite(Object body,
                                  MethodParameter returnType,
                                  @NonNull MediaType selectedContentType,
                                  @NonNull Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  @NonNull ServerHttpRequest request,
                                  @NonNull ServerHttpResponse response) {

        Object responseData = null;
        HttpResponseCode httpResponseCode = HttpSuccessResponseCode.OK;

        if (Void.TYPE.equals(returnType.getParameterType())) {
            responseData = DUMMY;
        } else {
            if (ObjectUtils.isEmpty(body)) {
                httpResponseCode = HttpSuccessResponseCode.NO_CONTENT;
            } else  {
                responseData = body;
            }
        }

        return ApiResponse.of(
                createSuccessCode(httpResponseCode),
                responseMessageReader.getMessage(httpResponseCode.getHttpStatus(), httpResponseCode.getMessageSuffixKey()),
                responseData
        );
    }

    private static class Dummy {
    }

    ;

    private String createSuccessCode(HttpResponseCode httpResponseCode) {
        return httpResponseCode.getHttpStatusValue() + DOMAIN_CODE + httpResponseCode.getResponseCode();
    }
}
