package com.minholee93.margincallcalculator.rest.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class TestController {

    @GetMapping("/test")
    public TestVo test() {

        LocalDateTime now = LocalDateTime.now();
        return TestVo.of(now, now);
    }

    @ToString
    @Getter
    @AllArgsConstructor
    public static class TestVo {
        private LocalDateTime createDateTime;
        private LocalDateTime updateDateTime;

        public static TestVo of(LocalDateTime createDateTime, LocalDateTime updateDateTime) {
            return new TestVo(createDateTime, updateDateTime);
        }
    }
}
