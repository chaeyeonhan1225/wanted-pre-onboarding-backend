package com.wanted.wantedpreonboardingbackend.service.dto;

import java.util.List;

public record CommonPage<T>(
        boolean hasNext,
        boolean hasPrevious,
        int totalPage,
        int totalSize,
        int currentPage,
        List<T> contents
) {
}
