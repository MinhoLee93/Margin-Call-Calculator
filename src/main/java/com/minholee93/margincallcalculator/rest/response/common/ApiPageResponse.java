package com.minholee93.margincallcalculator.rest.response.common;

import com.minholee93.margincallcalculator.rest.type.YnType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.domain.Page;

import java.util.Collections;
import java.util.List;

@Getter
@ToString
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ApiPageResponse<T> {

    private int currentPage;

    private long totalCount;

    private YnType lastPageYn;

    private List<T> elements;

    private static final ApiPageResponse<?> EMPTY =
            new ApiPageResponse<>(0, 0, YnType.Y, Collections.emptyList());

    @SuppressWarnings("unchecked")
    public static <T> ApiPageResponse<T> empty() {
        return (ApiPageResponse<T>) EMPTY;
    }

    public static <T> ApiPageResponse<T> of(int currentPage, long total, YnType lastPageYn, List<T> elements) {
        return new ApiPageResponse<>(currentPage, total, lastPageYn, elements);
    }

    public static <T> ApiPageResponse<T> of(Page<T> page) {
        return (page == null || page.isEmpty()) ? null :
                new ApiPageResponse<>(
                        page.getNumber() + 1,
                        page.getTotalElements(),
                        page.isLast() ? YnType.Y : YnType.N,
                        page.getContent()
                );
    }
}
