package com.minholee93.margincallcalculator.rest.controller;

import com.minholee93.margincallcalculator.rest.response.common.ApiPageResponse;
import com.minholee93.margincallcalculator.rest.type.YnType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Collections;

@Validated
@RestController
public class TestController {

    @GetMapping("/test")
    public TestResponseVo getTest() {

        LocalDateTime now = LocalDateTime.now();
        return TestResponseVo.of(now, now);
    }

    @GetMapping("/tests")
    public ApiPageResponse<TestResponseVo> getTestPage(@PageableDefault(size = 50) Pageable pageable,
                                                       @Valid @RequestBody TestRequestVo requestVo) {

        LocalDateTime now = LocalDateTime.now();

        return ApiPageResponse.of(
                pageable.getPageNumber(),
                1,
                YnType.Y,
                Collections.singletonList(TestResponseVo.of(now, now))
        );
    }

    @ToString
    @Getter
    @AllArgsConstructor
    public static class TestResponseVo {
        private LocalDateTime createDateTime;
        private LocalDateTime updateDateTime;

        public static TestResponseVo of(LocalDateTime createDateTime, LocalDateTime updateDateTime) {
            return new TestResponseVo(createDateTime, updateDateTime);
        }
    }

    @Getter
    @ToString
    public static class TestRequestVo {

        @NotNull
        private String test;
    }
}
