package com.minholee93.margincallcalculator.rest.response.common;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import static org.apache.commons.lang3.ArrayUtils.EMPTY_OBJECT_ARRAY;

@Component
@RequiredArgsConstructor
public class ResponseMessageReader {

    private final MessageSource messageSource;

    @Nullable
    public String getMessage(HttpStatus httpStatus, String suffixKey) {
        if (httpStatus != null && StringUtils.isNotBlank(suffixKey)) {
            String prefixKey = String.format("response.%s.", (httpStatus.isError() ? "error" : "success"));
            return messageSource.getMessage(
                    prefixKey + suffixKey.toLowerCase(),
                    EMPTY_OBJECT_ARRAY,
                    LocaleContextHolder.getLocale()
            );
        }
        return null;
    }
}
