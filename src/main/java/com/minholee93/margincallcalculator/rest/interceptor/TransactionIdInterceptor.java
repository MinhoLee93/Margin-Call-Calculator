package com.minholee93.margincallcalculator.rest.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.jboss.logging.MDC;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.AsyncHandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.minholee93.margincallcalculator.rest.constant.CommonConstant.DOMAIN_SHORT_NAME;

@SuppressWarnings("SpellCheckingInspection")
@Slf4j
public class TransactionIdInterceptor implements Ordered, AsyncHandlerInterceptor {

    private final DateTimeFormatter transactionIdDateTimeForamtter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        MDC.put("transactionId", createTransactionId());
        return true;
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }

    private String createTransactionId() {
        return DOMAIN_SHORT_NAME + LocalDateTime.now().format(transactionIdDateTimeForamtter) + nextCounter();
    }

    private String nextCounter() {
        String counter = Long.toString(System.currentTimeMillis());
        return counter.substring(counter.length() - 3);
    }
}
