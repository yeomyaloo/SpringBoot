package com.yeomyaloo.book.springboot.web.dto;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class HelloResponseDto {

    private final String name;
    private final int amount;

    public String getName() {
        return name;
    }

    public int getAmount() {
        return amount;
    }
}
