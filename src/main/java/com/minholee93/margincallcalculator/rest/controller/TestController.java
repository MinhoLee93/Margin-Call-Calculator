package com.minholee93.margincallcalculator.rest.controller;

import com.minholee93.margincallcalculator.rest.response.common.ApiPageResponse;
import com.minholee93.margincallcalculator.rest.type.YnType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Collections;

@RestController
public class TestController {

    @GetMapping("/test")
    public TestVo getTest() {

        LocalDateTime now = LocalDateTime.now();
        return TestVo.of(now, now);
    }

    @GetMapping("/tests")
    public ApiPageResponse<TestVo> getTestPage(@PageableDefault(size = 50) Pageable pageable) {

        LocalDateTime now = LocalDateTime.now();

        return ApiPageResponse.of(
                pageable.getPageNumber(),
                1,
                YnType.Y,
                Collections.singletonList(TestVo.of(now, now))
        );
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
