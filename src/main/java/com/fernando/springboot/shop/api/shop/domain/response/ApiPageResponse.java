package com.fernando.springboot.shop.api.shop.domain.response;

import java.time.Instant;
import java.util.List;

import org.springframework.data.domain.Page;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@NoArgsConstructor
public class ApiPageResponse<T> extends ApiResponse<List<T>> {

    private long totalElements;
    private int actualPage;
    private int pageSize;
    private int totalPages;
    private int numElements;
    private boolean hasNext;
    private boolean hasPrevious;
    
    public ApiPageResponse(
        String message,
        int code,
        Instant timestamp,
        Page<T> page
    ) {
        super(code, message, timestamp, page.getContent());
        totalElements = page.getTotalElements();
        actualPage = page.getNumber() + 1;
        totalPages = page.getTotalPages();
        pageSize = page.getSize();
        numElements = page.getContent().size();
        hasNext = page.hasNext();
        hasPrevious = page.hasPrevious();
    }
}
